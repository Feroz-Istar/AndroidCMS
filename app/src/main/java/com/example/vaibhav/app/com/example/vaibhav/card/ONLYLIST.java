package com.example.vaibhav.app.com.example.vaibhav.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLYLIST extends Card {

    private TextView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_list, container, false);
        list = (TextView) view.findViewById(R.id.list);
        List<String> lines = new ArrayList<>();

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");

            if(cms.getList() != null){
                new ThemeUtils().massageList(cms,list,getContext());


                }



        }


        return  view;

    }


}

