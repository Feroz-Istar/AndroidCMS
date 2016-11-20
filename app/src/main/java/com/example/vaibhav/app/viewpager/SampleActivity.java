package com.example.vaibhav.app.viewpager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vaibhav.app.cmspojo.CMSPresentation;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.R;
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
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        setContentView(R.layout.activity_sample);
        viewPager =(ViewPager) findViewById(R.id.view_pager);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        error_text= (TextView) findViewById(R.id.error_text);
        checkLogin(progressBar);

    }


    public void checkLogin(final ProgressBar progressBar) {
        error_text.setVisibility(View.GONE);
        final long t = System.currentTimeMillis();
        System.out.println("Here" + t);
        String BASE_URL = getResources().getString(R.string.server_ip) + "/get_ppt?ppt_id=111";
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
                    for(CMSSlide cmsSlide: cmsPresentation.getSlides()){
                        if(cmsSlide.getTemplateName() != null)
                            if(cmsSlide.getTemplateName().equalsIgnoreCase("ONLY_TITLE")
                                    ||cmsSlide.getTemplateName().equalsIgnoreCase("ONLY_TITLE_IMAGE")
                                    ||cmsSlide.getTemplateName().equalsIgnoreCase("ONLY_TITLE_LIST")
                                    ){
                                cmsSlides.add(cmsSlide);
                            }
                    }
                    viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),cmsSlides);
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


    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
