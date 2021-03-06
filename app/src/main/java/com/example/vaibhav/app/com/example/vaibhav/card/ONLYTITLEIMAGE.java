package com.example.vaibhav.app.com.example.vaibhav.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.CustomLayout;
import com.squareup.picasso.Picasso;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Feroz on 19/11/2016.
 */

public class ONLYTITLEIMAGE extends Card {
    private TextView title;
    private ImageView image;
    private CustomLayout main_layout;
    private Picasso mPicasso;
    private GifImageView gifImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_image, container, false);
        title = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.image);
        main_layout = (CustomLayout) view.findViewById(R.id.main_layout);
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        mPicasso = Picasso.with(getContext()); //Single instance
        gifImageView = (GifImageView) view.findViewById(R.id.mine);


        if(getArguments() != null){
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");

            if(cms != null){
               ThemeUtils themeUtils = new ThemeUtils();
                themeUtils.massageTitle(cms,title,getContext());
                themeUtils.massageBackgroundLayout(cms,mPicasso,main_layout,externalReadable,getContext(),gifImageView);


            if(cms.getImage() != null && cms.getImage().getUrl() != null){

                String url = "http://api.talentify.in/" + cms.getImage().getUrl();
                themeUtils.massageImage(url,mPicasso,image,externalReadable,getContext(),gifImageView);
            }
            }

        }
        return view;
    }
}
