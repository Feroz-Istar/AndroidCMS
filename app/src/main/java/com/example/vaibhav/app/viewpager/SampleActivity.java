package com.example.vaibhav.app.viewpager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.DefaultTransformer;
import com.eftimoff.viewpagertransformers.DepthPageTransformer;
import com.eftimoff.viewpagertransformers.ZoomOutSlideTransformer;
import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSPresentation;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveAudioVideoAsync;
import com.example.vaibhav.app.com.example.vaibhav.card.database.DatabaseHandler;
import com.example.vaibhav.app.mediautility.AudioVideoSaver;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.LockableViewPager;
import com.github.clans.fab.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SampleActivity extends AppCompatActivity {
    private LockableViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<CMSSlide> cmsSlides;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler;
    private TextView error_text;
    private DatabaseHandler databaseHandler;
    private int ppt_id;
    private int delay = 10000; //milliseconds
    private int page = 0;
    FloatingActionButton fab;
    private int mMaxProgress = 100, currentProgress = 0;
    private Runnable runnable, progreessRunnable;
    int clickcount = 0;
    private Handler mUiHandler;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        mUiHandler = new Handler();
        setContentView(R.layout.activity_sample);
        viewPager = (LockableViewPager) findViewById(R.id.view_pager);
        viewPager.setSwipeLocked(true);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setMax(mMaxProgress);
        fab.setShowProgressBackground(false);
        fab.setIndeterminate(false);
        fab.setColorNormal(Color.parseColor("#ff4444"));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickcount % 2 == 0) {
                    fab.setColorNormal(Color.parseColor("#00C851"));
                    fab.setImageResource(R.mipmap.ic_play_arrow_white_24dp);
                    handler.removeCallbacks(runnable);
                    mUiHandler.removeCallbacks(progreessRunnable);
                    viewPager.setSwipeLocked(false);

                } else {
                    fab.setImageResource(R.mipmap.ic_pause_white_24dp);
                    fab.setColorNormal(Color.parseColor("#ff4444"));
                    handler.postDelayed(runnable, delay);
                    mUiHandler.postDelayed(progreessRunnable, (delay / 100));
                    viewPager.setSwipeLocked(true);

                }
                clickcount++;
            }
        });

        viewPager.setOffscreenPageLimit(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                checkAudio(cmsSlides.get(position),mediaPlayer,getBaseContext());
                increaseProgress(fab, 0);
                if (null != viewPagerAdapter.getItem(position) && null != viewPagerAdapter.getItem(position).getArguments()
                        && viewPagerAdapter.getItem(position).getArguments().getString("TRANSITION") != null) {
                    viewPager.setPageTransformer(true, getPageTransoformer(viewPagerAdapter.getItem(position).getArguments().getString("TRANSITION")));
                } else {
                    viewPager.setPageTransformer(true, new DefaultTransformer());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        error_text = (TextView) findViewById(R.id.error_text);
        if (getIntent() != null) {
            Intent mIntent = getIntent();
            int intValue = Integer.parseInt(mIntent.getStringExtra("ppt_id"));
            databaseHandler = new DatabaseHandler(getBaseContext());
            ppt_id = intValue;
            Cursor c = databaseHandler.getData(ppt_id);
            if (c.moveToFirst()) {
                System.out.println("C is  nullldklkdkd");
                setObject(c.getString(1));
            } else {
                checkLogin(progressBar, intValue);
            }
        } else {
            checkLogin(progressBar, 0);
        }


        runnable = new Runnable() {
            public void run() {
                if (viewPager.getAdapter().getCount() == page) {
                    page = 0;
                } else {
                    page++;
                }
                viewPager.setCurrentItem(page, true);
                handler.postDelayed(this, delay);

            }
        };
        progreessRunnable = new Runnable() {
            @Override
            public void run() {
                increaseProgress(fab, currentProgress);
                mUiHandler.postDelayed(this, (delay / 100));
            }
        };
    }

    private void checkAudio(CMSSlide cmsSlide, MediaPlayer mediaPlayer, Context context) {
        //here we will check whether audio exist or not
        if(cmsSlide.getTitle() != null && cmsSlide.getTitle().getFragmentAudioUrl() != null){
            String url = "http://api.talentify.in/video/audio/" + cmsSlide.getTitle().getFragmentAudioUrl();
            int index = url.lastIndexOf("/");
            String audio_name = url.substring(index, url.length()).replace("/", "");
            AudioVideoSaver audioVideoSaver = new AudioVideoSaver(context).
                    setFileName(audio_name).
                    setExternal(ImageSaver.isExternalStorageReadable());
            Boolean file_exist = audioVideoSaver.checkFile();
            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.reset();
            }
            Uri videouri = null;

            if(file_exist){
                try {
                    videouri = Uri.fromFile(audioVideoSaver.load());

                    mediaPlayer.setDataSource(context,videouri);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else {
                try {
                    videouri = Uri.parse(url);
                    mediaPlayer.setDataSource(context,videouri);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new SaveAudioVideoAsync(audioVideoSaver).execute(url);


            }
        }else {
            mediaPlayer.reset();
        }

    }

    private void increaseProgress(final FloatingActionButton fab, int i) {
        if (i <= mMaxProgress) {
            fab.setProgress(i, false);
            currentProgress = ++i;
        }
    }

    public void checkLogin(final ProgressBar progressBar, final int ppt_id) {
        error_text.setVisibility(View.GONE);
        final long t = System.currentTimeMillis();
        System.out.println("Here" + t);
        String BASE_URL = "";
        if (ppt_id == 0) {
            BASE_URL = getResources().getString(R.string.server_ip) + "/get_ppt?ppt_id=416";
        } else {
            BASE_URL = getResources().getString(R.string.server_ip) + "/get_ppt?ppt_id=" + ppt_id;
        }
        System.out.println("BASE_URL " + BASE_URL);
        AsyncHttpClient myClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        myClient.setTimeout(120000);
        myClient.setLoggingEnabled(false);
        myClient.get(BASE_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(SampleActivity.this, "Something went wrong with server ...",
                        Toast.LENGTH_LONG).show();
                error_text.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onStart() {
                // called before request is starte
                progressBar.setVisibility(View.VISIBLE);
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String xml_object = responseString;
                xml_object = xml_object.replaceAll("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
                databaseHandler.saveContent(ppt_id + "", xml_object);
                setObject(xml_object);
                progressBar.setVisibility(View.GONE);

            }


            @Override
            public void onRetry(int retryNo) {
            }
        });


    }

    public ViewPager.PageTransformer getPageTransoformer(String transformer) {

        ViewPager.PageTransformer pageTransformer = new ZoomOutSlideTransformer();
        System.out.println("Selected transition---------" + transformer);

        switch (transformer) {
            case "slide":
                pageTransformer = new DefaultTransformer();
                break;
            case "zoom":
                pageTransformer = new ZoomOutSlideTransformer();
                break;
            default:
                pageTransformer = new DepthPageTransformer();
                break;
        }

        return pageTransformer;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SampleActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void setObject(String xml_object) {
        StringReader reader = new StringReader(xml_object);
        Serializer serializer = new Persister();
        try {
            CMSPresentation cmsPresentation = serializer.read(CMSPresentation.class, reader);
            System.out.println("total time " + cmsPresentation.getSlides().size());
            //System.out.println("total time " + time_taken);
            cmsSlides = new ArrayList<>();
//
            for (CMSSlide cmsSlide : cmsPresentation.getSlides()) {

                cmsSlides.add(cmsSlide);
            }
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), cmsSlides);
            viewPager.setAdapter(viewPagerAdapter);

            checkAudio(cmsSlides.get(0),mediaPlayer,getBaseContext());

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mUiHandler.postDelayed(progreessRunnable, (delay / 100));
        handler.postDelayed(runnable, delay);

        if(mediaPlayer != null){
            mediaPlayer.start();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        mUiHandler.postDelayed(progreessRunnable, (delay / 100));
        handler.removeCallbacks(runnable);
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    public void onDestroy(){
        if(mediaPlayer != null ){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;        }
        super.onDestroy();
    }


}
