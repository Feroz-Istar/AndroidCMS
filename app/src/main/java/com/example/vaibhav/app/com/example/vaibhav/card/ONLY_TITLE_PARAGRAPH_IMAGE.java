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
import com.squareup.picasso.Picasso;

/**
 * Created by Feroz on 23/11/2016.
 */

public class ONLY_TITLE_PARAGRAPH_IMAGE extends Card {
    private TextView title,paragraph;
    private ImageView image;
    private Picasso mPicasso;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_paragraph_image, container, false);
        title = (TextView) view.findViewById(R.id.title);
        paragraph = (TextView) view.findViewById(R.id.paragraph);
        image = (ImageView) view.findViewById(R.id.image);
        mPicasso = Picasso.with(getContext()); //Single instance

        if (getArguments() != null) {

            CMSSlide cms = null;
            if(getArguments().getSerializable("CMSSLIDE") != null)
                cms= (CMSSlide) getArguments().getSerializable("CMSSLIDE");

            if(cms != null){
                if(cms.getTitle() != null && cms.getTitle().getText() != null)
                    title.setText(cms.getTitle().getText());

                if(cms.getParagraph() != null && cms.getParagraph().getText() != null){
                    paragraph.setText(cms.getParagraph().getText());
                }

                if(cms.getImage() != null && cms.getImage().getUrl() != null){
                    mPicasso.load("http://api.talentify.in" + cms.getImage().getUrl()).into(image);

                }

            }
        }

            return view;
    }
}
