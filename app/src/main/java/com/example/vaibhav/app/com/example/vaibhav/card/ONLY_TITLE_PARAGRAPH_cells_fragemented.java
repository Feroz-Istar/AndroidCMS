package com.example.vaibhav.app.com.example.vaibhav.card;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.CustomLayout;
import com.squareup.picasso.Picasso;

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLY_TITLE_PARAGRAPH_cells_fragemented extends Card {

    private TextView paragraph,title;
    private Picasso mPicasso;
    private CustomLayout main_layout;
    private MediaPlayer mPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_paragraph_cell_fragmented, container, false);
        paragraph = (TextView) view.findViewById(R.id.paragraph);
        title = (TextView) view.findViewById(R.id.title);
        mPicasso = Picasso.with(getContext());
        main_layout = (CustomLayout) view.findViewById(R.id.main_layout);
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        mPlayer = new MediaPlayer();

        if (getArguments() != null) {
            CMSSlide cms = null;
            cms= (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if(cms != null){
                ThemeUtils themeUtils = new ThemeUtils();
                themeUtils.massageTitle(cms,title,getContext(),mPlayer);
                themeUtils.massageParagraph(cms,paragraph,getContext());
                themeUtils.massageBackgroundLayout(cms,mPicasso,main_layout,externalReadable,getContext());
                try {
                    mPlayer.prepare();
                }catch (Exception e){

                }}

        }

        return view;
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mPlayer != null ) {
                mPlayer.start();
            }
        } else {
            if (mPlayer != null && mPlayer.isPlaying()) {
                mPlayer.pause();
                mPlayer.seekTo(0);
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.reset(); // Might not be necessary, since release() is called right after, but it doesn't seem to hurt/cause issues
            mPlayer.release();
            mPlayer = null;
        }
        super.onDestroy();

    }

    @Override
    public void onPause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.reset(); // Might not be necessary, since release() is called right after, but it doesn't seem to hurt/cause issues
            mPlayer.release();
            mPlayer = null;
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.reset(); // Might not be necessary, since release() is called right after, but it doesn't seem to hurt/cause issues
            mPlayer.release();
            mPlayer = null;
        }
        super.onStop();
    }

}
