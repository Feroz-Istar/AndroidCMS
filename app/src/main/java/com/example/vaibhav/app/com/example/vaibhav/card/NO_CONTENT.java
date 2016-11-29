package com.example.vaibhav.app.com.example.vaibhav.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.squareup.picasso.Picasso;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Feroz on 23/11/2016.
 */

public class NO_CONTENT extends Card {

    private ImageView image;
    private Picasso mPicasso;
    private GifImageView gifImageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.no_content, container, false);
        image = (ImageView) view.findViewById(R.id.image);
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        mPicasso = Picasso.with(getContext());
        gifImageView = (GifImageView) view.findViewById(R.id.mine);
        if (getArguments() != null) {
            CMSSlide cms = null;
            if (getArguments().getSerializable("CMSSLIDE") != null)
                cms = (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if (cms != null && cms.getImage_BG() != null && !cms.getImage_BG().equalsIgnoreCase("none")) {
                String url = "http://api.talentify.in/"+cms.getImage_BG();
                new ThemeUtils().massageImage(url, mPicasso, image, externalReadable, getContext(),gifImageView);
            }
        }
            return view;

        }



}
