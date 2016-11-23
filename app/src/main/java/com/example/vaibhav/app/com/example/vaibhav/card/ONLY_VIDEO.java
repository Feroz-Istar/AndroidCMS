package com.example.vaibhav.app.com.example.vaibhav.card;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
                    try {
                        // Start the MediaController
                        MediaController mediacontroller = new MediaController(getContext());
                        mediacontroller.setAnchorView(video);
                        // Get the URL from String VideoURL
                        Uri videoss = Uri.parse("http://api.talentify.in/"+cms.getVideo().getUrl());
                        video.setMediaController(mediacontroller);
                        video.setVideoURI(videoss);

                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }

                    video.requestFocus();
                    video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        // Close the progress bar and play the video
                        public void onPrepared(MediaPlayer mp) {
                            video.start();
                        }
                    });


                }
            }
        }
        return  view;
    }
}
