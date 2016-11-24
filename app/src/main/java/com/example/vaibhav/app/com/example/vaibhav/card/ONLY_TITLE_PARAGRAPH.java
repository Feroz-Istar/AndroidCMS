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

/**
 * Created by Feroz on 23/11/2016.
 */

public class ONLY_TITLE_PARAGRAPH extends Card {

    private TextView paragraph,title;
    private Picasso mPicasso;
    private CustomLayout main_layout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_paragraph, container, false);
        paragraph = (TextView) view.findViewById(R.id.paragraph);
        title = (TextView) view.findViewById(R.id.title);
        mPicasso = Picasso.with(getContext());
        main_layout = (CustomLayout) view.findViewById(R.id.main_layout);
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        if (getArguments() != null) {
            CMSSlide cms = null;
                cms= (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if(cms != null) {
                ThemeUtils themeUtils = new ThemeUtils();
                themeUtils.massageTitle(cms, title, getContext());
                themeUtils.massageParagraph(cms, paragraph, getContext());
                themeUtils.massageBackgroundLayout(cms, mPicasso, main_layout, externalReadable, getContext());
            }

        }

        return view;
    }
}
