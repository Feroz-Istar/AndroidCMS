package com.example.vaibhav.app.com.example.vaibhav.card;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLY_PARAGRAPH extends Card {

    private TextView paragraph,title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_paragraph, container, false);
        paragraph = (TextView) view.findViewById(R.id.paragraph);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Regular.ttf");

        if (getArguments() != null) {
            CMSSlide cms = null;
            if (getArguments().getSerializable("CMSSLIDE") != null) {
                cms = (CMSSlide) getArguments().getSerializable("CMSSLIDE");

                if (cms != null) {

                    if(cms.getParagraph() != null && cms.getParagraph().getText() != null){
                        paragraph.setText(Html.fromHtml(cms.getParagraph().getText()));
                        paragraph.setTypeface(tf);
                    }
                    if(cms.getTheme() != null && cms.getTheme().getParagraphFontColor() != null){
                        paragraph.setTextColor(Color.parseColor(cms.getTheme().getParagraphFontColor()));
                        paragraph.setTextSize(Integer.parseInt(cms.getTheme().getParagraphFontSize())/3);

                    }
                }

            }
        }
        return view;

    }
    }
