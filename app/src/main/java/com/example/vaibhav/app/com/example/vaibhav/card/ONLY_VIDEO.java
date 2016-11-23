package com.example.vaibhav.app.com.example.vaibhav.card;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;

/**
 * Created by Feroz on 23/11/2016.
 */

public class ONLY_VIDEO extends Card {
    private VideoView video;
    private MediaController mediaControls;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_video, container, false);
        video = (VideoView) view.findViewById(R.id.video);

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if (cms != null) {

                if(cms.getVideo() != null && cms.getVideo().getUrl() != null){
                    try{
                        video.setMediaController(mediaControls);
                        video.setVideoURI(Uri.parse("http://api.talentify.in/"+cms.getVideo().getUrl()));
                        video.requestFocus();
                        video.start();
                    }catch (Exception e){

                    }

                }
            }
        }
        return  view;
    }
}
