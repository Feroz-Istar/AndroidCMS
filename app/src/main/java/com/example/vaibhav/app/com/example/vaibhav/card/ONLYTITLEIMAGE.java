package com.example.vaibhav.app.com.example.vaibhav.card;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.mediautility.ImageSaver;

import java.net.URL;

/**
 * Created by Feroz on 19/11/2016.
 */

public class ONLYTITLEIMAGE extends Card {
    TextView title;
    ImageView image;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_image, container, false);
        title = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.image);
        Boolean externalReadable = new ImageSaver(getContext()).isExternalStorageReadable();
        Boolean externalWritable = new ImageSaver(getContext()).isExternalStorageWritable();
        Typeface titletf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Thin.ttf");

        if(getArguments() != null){
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");
            if(cms.getTitle() != null){
                title.setText(cms.getTitle().getText());
                title.setTypeface(titletf);
            }
            if(cms.getTheme() != null && cms.getTheme().getTitleFontColor() != null){
                title.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
                title.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize())/3);
                title.setBackgroundColor(Color.parseColor(cms.getTheme().getBackgroundColor()));
            }

            if(cms.getImage() != null && cms.getImage().getUrl() != null){
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    URL url = new URL("http://api.talentify.in/" + cms.getImage().getUrl());
                    //file already exist or not
                    int index = cms.getImage().getUrl().lastIndexOf("/");
                    String bg_image_name = cms.getImage().getUrl().substring(index, cms.getImage().getUrl().length()).replace("/", "");
                    Boolean file_exist = new ImageSaver(getContext()).
                            setFileName(bg_image_name).
                            setExternal(externalReadable).
                            checkFile();
                    if (file_exist) {
                       Bitmap bitmap = new ImageSaver(getContext()).
                                setFileName(bg_image_name).
                                setExternal(externalReadable).
                                load();
                        image.setImageBitmap(bitmap);
                    }else {
                        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        image.setImageBitmap(bmp);
                        if (bmp != null) {
                            new ImageSaver(getContext()).
                                    setFileName(bg_image_name).
                                    setExternal(externalWritable).
                                    save(bmp);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        }
        return view;
    }
}
