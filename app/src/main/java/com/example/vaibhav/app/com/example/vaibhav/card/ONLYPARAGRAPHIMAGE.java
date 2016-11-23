package com.example.vaibhav.app.com.example.vaibhav.card;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
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
public class ONLYPARAGRAPHIMAGE extends Card {

    private TextView title,paragraph;
    private ImageView image;
    private Picasso mPicasso;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_paragraph_image, container, false);
        title = (TextView) view.findViewById(R.id.title);
        paragraph = (TextView) view.findViewById(R.id.paragraph);
        image = (ImageView) view.findViewById(R.id.image);
        mPicasso = Picasso.with(getContext()); //Single instance
        Typeface descriptiontf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Regular.ttf");

        if (getArguments() != null) {

            CMSSlide cms = null;
            if(getArguments().getSerializable("CMSSLIDE") != null)
                cms= (CMSSlide) getArguments().getSerializable("CMSSLIDE");

            if(cms != null){

                if(cms.getParagraph() != null && cms.getParagraph().getText() != null){
                    paragraph.setText(Html.fromHtml(cms.getParagraph().getText()));
                    paragraph.setTypeface(descriptiontf);
                }
                if(cms.getTheme() != null && cms.getTheme().getParagraphFontColor() != null){
                    paragraph.setTextColor(Color.parseColor(cms.getTheme().getParagraphFontColor()));
                    paragraph.setTextSize(Integer.parseInt(cms.getTheme().getParagraphFontSize())/3);

                }
                if(cms.getImage() != null && cms.getImage().getUrl() != null){
                    mPicasso.load("http://api.talentify.in" + cms.getImage().getUrl()).fit().into(image);

                }

            }
        }

        return view;
    }
}
