package com.example.vaibhav.app.com.example.vaibhav.card;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vaibhav.app.CMSSlide;
import com.example.vaibhav.app.R;

/**
 * Created by Vaibhav on 18-11-2016.
 */

public class ONLYTITLE extends  Card{
    public final static String EXTRA_POSITION = "extra_position";
    public final static String Title = "title";
    private LinearLayout main_layout;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title, container, false);
        textView = (TextView) view.findViewById(R.id.text);
        main_layout = (LinearLayout) view.findViewById(R.id.main_layout);
        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");
            if(cms.getTitle() != null){
                textView.setText(cms.getTitle().getText());
                textView.setTextSize(Float.parseFloat(cms.getTheme().getTitleFontSize())/4);
                textView.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
                main_layout.setBackgroundColor(Color.parseColor(cms.getTheme().getBackgroundColor()));
            }
        }
        return  view;
    }


}
