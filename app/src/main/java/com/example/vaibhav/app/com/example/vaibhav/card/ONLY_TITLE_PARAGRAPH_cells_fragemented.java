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
public class ONLY_TITLE_PARAGRAPH_cells_fragemented extends Card {

    private TextView paragraph,title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_paragraph_cell_fragmented, container, false);
        paragraph = (TextView) view.findViewById(R.id.paragraph);
        title = (TextView) view.findViewById(R.id.title);
        Typeface titletf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Thin.ttf");
        Typeface descriptiontf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Thin.ttf");
        if (getArguments() != null) {
            CMSSlide cms = null;
            cms= (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if(cms != null){
                if(cms.getTitle() != null && cms.getTitle().getText() != null) {
                    title.setText(cms.getTitle().getText());
                    title.setTypeface(titletf);
                }
                if(cms.getTheme() != null && cms.getTheme().getTitleFontColor() != null){
                    title.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
                    title.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize())/3);
                }


                if(cms.getParagraph() != null && cms.getParagraph().getText() != null){
                    paragraph.setText(Html.fromHtml(cms.getParagraph().getText()));
                    paragraph.setTypeface(descriptiontf);
                }
            }

        }

        return view;
    }

}
