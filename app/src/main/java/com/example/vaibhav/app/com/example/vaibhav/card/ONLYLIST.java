package com.example.vaibhav.app.com.example.vaibhav.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.CustomLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLYLIST extends Card {

    private TextView list;
    private Picasso mPicasso;
    private CustomLayout main_layout;
    private GifImageView gifImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_list, container, false);
        list = (TextView) view.findViewById(R.id.list);
        List<String> lines = new ArrayList<>();
        main_layout = (CustomLayout) view.findViewById(R.id.main_layout);
        mPicasso = Picasso.with(getContext());
        gifImageView = (GifImageView) view.findViewById(R.id.mine);

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide) getArguments().getSerializable("CMSSLIDE");

            if (cms.getList() != null) {
                ThemeUtils themeUtils = new ThemeUtils();
                themeUtils.massageList(cms, list, getContext());
                themeUtils.massageBackgroundLayout(cms, mPicasso, main_layout, ImageSaver.isExternalStorageReadable(), getContext(), gifImageView);
            }
        }
        return view;

    }


}

