package com.example.vaibhav.app.com.example.vaibhav.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.mediautility.ImageSaver;

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
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if (cms != null) {
                new ThemeUtils().massageVideo(cms,video,externalReadable,getContext());
            }
        }
        return  view;
    }

    @Override
    public void onDestroy() {
        if (video != null && video.isPlaying()) {
            video.stopPlayback();
            video = null;
        }
        super.onDestroy();

    }


}
