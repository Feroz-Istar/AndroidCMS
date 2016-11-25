package com.example.vaibhav.app.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.AccordionTransformer;
import com.eftimoff.viewpagertransformers.BackgroundToForegroundTransformer;
import com.eftimoff.viewpagertransformers.CubeOutTransformer;
import com.eftimoff.viewpagertransformers.DepthPageTransformer;
import com.eftimoff.viewpagertransformers.FlipVerticalTransformer;
import com.eftimoff.viewpagertransformers.RotateUpTransformer;
import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSPresentation;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class SampleActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<CMSSlide> cmsSlides;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private TextView error_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sample);
        viewPager = (ViewPager) findViewById(R.id.view_pager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setPageTransformer(true, getPageTransoformer(position));
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
            checkLogin(progressBar, intValue);

        } else {
            checkLogin(progressBar, 0);
        }
    }


    public void checkLogin(final ProgressBar progressBar, int ppt_id) {
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

                } catch (Exception e) {
                    e.printStackTrace();

                }


                progressBar.setVisibility(View.GONE);

            }


            @Override
            public void onRetry(int retryNo) {
            }
        });


    }

    public ViewPager.PageTransformer getPageTransoformer(int position) {

        int random = randInt(0, 3);
        ViewPager.PageTransformer pageTransformer = new AccordionTransformer();
        System.out.println("Random Transform---------" + random);
        switch (random) {
            case 0:
                pageTransformer = new AccordionTransformer();
                break;
            case 1:
                pageTransformer = new BackgroundToForegroundTransformer();
                break;
            case 2:
                pageTransformer = new FlipVerticalTransformer();
                break;
            case 3:
                pageTransformer = new CubeOutTransformer();
                break;
            default:
                pageTransformer = new DepthPageTransformer();
                break;
        }
        return pageTransformer;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SampleActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
