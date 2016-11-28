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
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.CustomLayout;
import com.squareup.picasso.Picasso;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Feroz on 23/11/2016.
 */

public class ONLY_TITLE_PARAGRAPH_IMAGE extends Card {
    private TextView title, paragraph;
    private ImageView image;
    private Picasso mPicasso;
    private CustomLayout main_layout;
    private GifImageView gifImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.only_title_paragraph_image, container, false);
        title = (TextView) view.findViewById(R.id.title);
        paragraph = (TextView) view.findViewById(R.id.paragraph);
        image = (ImageView) view.findViewById(R.id.image);
        main_layout = (CustomLayout) view.findViewById(R.id.main_layout);
        mPicasso = Picasso.with(getContext()); //Single instance
        gifImageView = (GifImageView) view.findViewById(R.id.mine);

        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        if (getArguments() != null) {
            CMSSlide cms = null;
            if (getArguments().getSerializable("CMSSLIDE") != null)
                cms = (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if (cms != null) {
                ThemeUtils themeUtils = new ThemeUtils();
                themeUtils.massageTitle(cms, title, getContext());

                themeUtils.massageParagraph(cms, paragraph, getContext());
                themeUtils.massageBackgroundLayout(cms, mPicasso, main_layout, externalReadable, getContext(),gifImageView);
                if (cms.getImage() != null && cms.getImage().getUrl() != null) {
                    String url = "http://api.talentify.in" + cms.getImage().getUrl();
                    themeUtils.massageImage(url, mPicasso, image, externalReadable, getContext(),gifImageView);
                }
            }
        }

        return view;
    }
}
