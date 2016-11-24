package com.example.vaibhav.app.com.example.vaibhav.card;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveAudioVideoAsync;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveImageAsync;
import com.example.vaibhav.app.mediautility.AudioVideoSaver;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.CustomLayout;
import com.squareup.picasso.Picasso;

/**
 * Created by Feroz on 24/11/2016.
 */

public class ThemeUtils {

    public void massageTitle(CMSSlide cms, TextView title, Context context) {
        Typeface titletf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");

        if (cms.getTitle() != null && cms.getTitle().getText() != null) {
            title.setText(cms.getTitle().getText());
            title.setTypeface(titletf, Typeface.BOLD);
        }
        if (cms.getTheme() != null && cms.getTheme().getTitleFontColor() != null) {
            title.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
            title.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize()) / 3);
        }

    }

    public void massageTitle2(CMSSlide cms, TextView title, Context context) {
        Typeface titletf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");
        if (cms.getTitle() != null && cms.getTitle().getText() != null) {
            title.setText(cms.getTitle().getText());
            title.setTypeface(titletf);
        }
        if (cms.getTheme() != null && cms.getTheme().getTitleFontColor() != null) {
            title.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
            title.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize()) / 3);
        }

    }


    public void massageParagraph(CMSSlide cms, TextView paragraph, Context context) {
        Typeface paragraphtf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");

        if (cms.getParagraph() != null && cms.getParagraph().getText() != null) {
            paragraph.setText(Html.fromHtml(cms.getParagraph().getText()));
            paragraph.setTypeface(paragraphtf);
            paragraph.setTextColor(Color.parseColor(cms.getTheme().getParagraphFontColor()));
            paragraph.setTextSize(Integer.parseInt(cms.getTheme().getParagraphFontSize()) / 3);

        }
    }

    public void massageBackgroundLayout(CMSSlide cms, Picasso mPicasso, CustomLayout main_layout, Boolean externalReadable, Context context) {
        if (cms.getImage_BG() != null && !cms.getImage_BG().equalsIgnoreCase("none") && !cms.getImage_BG().equalsIgnoreCase("null")) {
            int index = cms.getImage_BG().lastIndexOf("/");
            System.out.println("cms.getImage_BG() "+cms.getImage_BG()+" index "+ index);
            String bg_image_name = cms.getImage_BG().substring(index, cms.getImage_BG().length()).replace("/", "");
            ImageSaver imageSaver = new ImageSaver(context).
                    setFileName(bg_image_name).
                    setExternal(externalReadable);
            Boolean file_exist = imageSaver.checkFile();

            if(cms.getBackground() != null){
                if(!cms.getBackground().equalsIgnoreCase("#ffffff") && !cms.getBackground().equalsIgnoreCase("#000000") && !cms.getBackground().equalsIgnoreCase("none")){
                    main_layout.setBackgroundColor(Color.parseColor(cms.getBackground()));
                }else{
                    if(cms.getTheme() != null && cms.getTheme().getBackgroundColor() != null)
                        main_layout.setBackgroundColor(Color.parseColor(cms.getTheme().getBackgroundColor()));
                }

            }

            if (file_exist) {
                Bitmap bitmap = imageSaver.setExternal(externalReadable).load();
                BitmapDrawable background = new BitmapDrawable(bitmap);
                main_layout.setBackgroundDrawable(background);
            } else {
                mPicasso.load("http://api.talentify.in" + cms.getImage_BG()).into(main_layout);
                new SaveImageAsync(imageSaver).execute("http://api.talentify.in/" + cms.getImage_BG());
            }
        }
    }


    public void massageImage(String url, Picasso mPicasso, ImageView imageView, Boolean externalReadable, Context context) {
        int index = url.lastIndexOf("/");
        String bg_image_name = url.substring(index, url.length()).replace("/", "");
        ImageSaver imageSaver = new ImageSaver(context).
                setFileName(bg_image_name).
                setExternal(externalReadable);
        Boolean file_exist = imageSaver.checkFile();
        if (file_exist) {
            Bitmap bitmap = imageSaver.setExternal(externalReadable).load();
            imageView.setImageBitmap(bitmap);
        } else {
            mPicasso.load(url).fit().into(imageView);
            new SaveImageAsync(imageSaver).execute(url);
        }
    }

    public void massageVideo(CMSSlide cms, final VideoView video,Boolean externalReadable,Context context) {
        if (cms.getVideo() != null && cms.getVideo().getUrl() != null) {
            String url= "http://api.talentify.in/"+cms.getVideo().getUrl();
            int index = url.lastIndexOf("/");
            String bg_image_name = url.substring(index, url.length()).replace("/", "");
            AudioVideoSaver audioVideoSaver = new AudioVideoSaver(context).
                    setFileName(bg_image_name+".mp4").
                    setExternal(externalReadable);
            Boolean file_exist = audioVideoSaver.checkFile();
            Uri videouri = null;
            if(file_exist){
                System.out.println("VIdeo existr just play it");
                videouri= Uri.fromFile(audioVideoSaver.load());
            }else {
                try {
                    videouri= Uri.parse(url);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                new SaveAudioVideoAsync(audioVideoSaver).execute(url);
            }
            MediaController mediacontroller = new MediaController(context);
            mediacontroller.setAnchorView(video);
            video.setMediaController(mediacontroller);
            video.setVideoURI(videouri);
                video.requestFocus();
                video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                        video.start();
                    }
                });


        }
    }
}