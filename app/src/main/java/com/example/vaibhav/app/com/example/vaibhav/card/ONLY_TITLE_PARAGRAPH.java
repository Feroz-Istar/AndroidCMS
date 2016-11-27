package com.example.vaibhav.app.com.example.vaibhav.card;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveGifAsync;
import com.example.vaibhav.app.mediautility.GifImageSaver;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.CustomLayout;
import com.squareup.picasso.Picasso;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Feroz on 23/11/2016.
 */

public class ONLY_TITLE_PARAGRAPH extends Card {

    private TextView title;
    private WebView paragraph;
    private Picasso mPicasso;
    private CustomLayout main_layout;
    private GifImageView gifImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_paragraph, container, false);
        paragraph = (WebView) view.findViewById(R.id.paragraph);
        title = (TextView) view.findViewById(R.id.title);
        mPicasso = Picasso.with(getContext());
        main_layout = (CustomLayout) view.findViewById(R.id.main_layout);
        gifImageView = (GifImageView) view.findViewById(R.id.mine);

        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        if (getArguments() != null) {
            CMSSlide cms = null;
                cms= (CMSSlide) getArguments().getSerializable("CMSSLIDE");
            if(cms != null) {
                ThemeUtils themeUtils = new ThemeUtils();
                themeUtils.massageTitle(cms, title, getContext());
                themeUtils.massageParagraph(cms, paragraph, getContext());

                if (cms != null && cms.getImage_BG() != null) {
                    String url = "http://api.talentify.in/" + cms.getImage_BG();

                    if (cms.getImage_BG().contains(".gif")) {
                        checkGIFImage(url, getContext(), gifImageView);
                    } else {

                        themeUtils.massageBackgroundLayout(cms, mPicasso, main_layout, externalReadable, getContext());

                    }
                }
               }

        }//transition="slide"

        return view;
    }


    private void checkGIFImage(String url, Context context, GifImageView gifImageView) {
        int index = url.lastIndexOf("/");
        String bg_image_name = url.substring(index, url.length()).replace("/", "");
        GifImageSaver imageSaver = new GifImageSaver(context).
                setFileName(bg_image_name).
                setExternal(GifImageSaver.isExternalStorageReadable());
        Boolean file_exist = imageSaver.checkFile();
        gifImageView.setVisibility(View.VISIBLE);

        if (file_exist) {
            try {
                gifImageView.setImageDrawable(new GifDrawable(imageSaver.pathFile()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Glide.with(context).load(url).asGif().into(gifImageView);
            new SaveGifAsync(imageSaver).execute(url);

        }
    }
}
