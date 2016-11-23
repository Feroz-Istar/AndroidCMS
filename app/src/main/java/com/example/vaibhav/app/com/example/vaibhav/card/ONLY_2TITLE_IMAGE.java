package com.example.vaibhav.app.com.example.vaibhav.card;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.squareup.picasso.Picasso;

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLY_2TITLE_IMAGE extends Card {

    private TextView title1,title2;
    private Picasso mPicasso;
    private ImageView image;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_2title_image, container, false);
        title1 = (TextView) view.findViewById(R.id.title1);
        title2 = (TextView) view.findViewById(R.id.title2);
        image = (ImageView) view.findViewById(R.id.image);
        mPicasso = Picasso.with(getContext());
        Typeface titletf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Thin.ttf");
        Typeface descriptiontf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Thin.ttf");
        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if(cms != null){
                if( cms.getTitle().getText()!= null ) {
                    title1.setText(cms.getTitle().getText());
                    title1.setTypeface(titletf);
                }
                if( cms.getTitle2().getText() != null ) {
                    title2.setText(cms.getTitle2().getText());
                    title2.setTypeface(descriptiontf);
                }
                if(cms.getImage() != null && cms.getImage().getUrl() != null)
                    mPicasso.load("http://api.talentify.in" + cms.getImage().getUrl()).into(image);



            }
        }



        return view;

    }
}
