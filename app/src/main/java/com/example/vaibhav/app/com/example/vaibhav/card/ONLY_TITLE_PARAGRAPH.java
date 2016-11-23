package com.example.vaibhav.app.com.example.vaibhav.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;

/**
 * Created by Feroz on 23/11/2016.
 */

public class ONLY_TITLE_PARAGRAPH extends Card {

    private TextView paragraph,title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_paragraph, container, false);
        paragraph = (TextView) view.findViewById(R.id.paragraph);
        title = (TextView) view.findViewById(R.id.title);

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
            }

        }

        return view;
    }
}