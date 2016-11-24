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

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLY_2TITLE_IMAGE extends Card {

    private TextView title1,title2;
    private Picasso mPicasso;
    private ImageView image;
    private CustomLayout main_layout;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_2title_image, container, false);
        title1 = (TextView) view.findViewById(R.id.title1);
        title2 = (TextView) view.findViewById(R.id.title2);
        image = (ImageView) view.findViewById(R.id.image);
        main_layout = (CustomLayout) view.findViewById(R.id.main_layout);
        mPicasso = Picasso.with(getContext());
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if(cms != null){
                ThemeUtils themeUtils = new ThemeUtils();
                themeUtils.massageTitle(cms,title1,getContext());
                themeUtils.massageTitle2(cms,title2,getContext());
                if(cms.getImage() != null && cms.getImage().getUrl() != null) {
                    String url = "http://api.talentify.in" + cms.getImage().getUrl();
                    themeUtils.massageImage(url,mPicasso,image,externalReadable,getContext());
                }
                themeUtils.massageBackgroundLayout(cms,mPicasso,main_layout,externalReadable,getContext());

            }
        }



        return view;

    }
}
