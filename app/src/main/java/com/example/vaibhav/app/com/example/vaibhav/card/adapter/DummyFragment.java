package com.example.vaibhav.app.com.example.vaibhav.card.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.com.example.vaibhav.card.Card;

/**
 * Created by Feroz on 22/11/2016.
 */

public class DummyFragment extends Card {
    public final static String Title = "title";
    private LinearLayout main_layout;
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title, container, false);
        textView = (TextView) view.findViewById(R.id.text);
        main_layout = (LinearLayout) view.findViewById(R.id.main_layout);
        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");
            if(cms.getTitle() != null){
                textView.setText(cms.getTitle().getText());


                if(cms.getTheme().getTitleFontSize() != null)
                    textView.setTextSize(Float.parseFloat(cms.getTheme().getTitleFontSize())/3);
                if(cms.getTheme().getTitleFontColor() !=null)
                    textView.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
                if(cms.getTheme().getBackgroundColor() != null)
                    main_layout.setBackgroundColor(Color.parseColor(cms.getTheme().getBackgroundColor()));
            }
        }
        return  view;
    }
}
