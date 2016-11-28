package com.example.vaibhav.app.mediautility;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.example.vaibhav.app.cmspojo.CMSPresentation;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveAudioVideoAsync;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveGifAsync;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveImageAsync;
import com.example.vaibhav.app.com.example.vaibhav.card.database.DatabaseHandler;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;

/**
 * Created by Ajith on 28-11-2016.
 */

public class CMSResourceUtility {
    private Context context;
    private String xml;
    private DatabaseHandler databaseHandler;
    private int ppt_id;

    public CMSResourceUtility(Context context, String xml, int ppt_id) {
        this.context = context;
        this.xml = xml;
        this.databaseHandler = new DatabaseHandler(context);
        this.ppt_id = ppt_id;
    }

    public void fetchResource() {
        xml = xml.replaceAll("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        databaseHandler.saveContent(ppt_id + "", xml);
        StringReader reader = new StringReader(xml);
        Serializer serializer = new Persister();
        try {
            CMSPresentation cmsPresentation = serializer.read(CMSPresentation.class, reader);
            for (CMSSlide cmsSlide : cmsPresentation.getSlides()) {
                getBackgroundImage(cmsSlide);
                getImage(cmsSlide);
                getAudio(cmsSlide);
                getVideo(cmsSlide);
            }

        } catch (Exception e) {

        }

    }

    private void getVideo(CMSSlide cmsSlide) {
        if(cmsSlide != null && cmsSlide.getVideo() != null && cmsSlide.getVideo().getUrl() != null){
            saveAudioOrVideo("http://api.talentify.in/video/audio/"+cmsSlide.getVideo().getUrl());
        }

    }

    private void getAudio(CMSSlide cmsSlide) {

            if(cmsSlide != null && cmsSlide.getAudioUrl() != null){

                String url = "http://api.talentify.in/video/audio/" + cmsSlide.getAudioUrl();
                saveAudioOrVideo(url);
            }


    }

    private void saveAudioOrVideo(String url) {
        try {
            int index = url.lastIndexOf("/");
            String file_name = url.substring(index, url.length()).replace("/", "");
            AudioVideoSaver audioVideoSaver = new AudioVideoSaver(context).setFileName(file_name).setExternal(ImageSaver.isExternalStorageReadable());
            new SaveAudioVideoAsync(audioVideoSaver).execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getImage(CMSSlide cmsSlide) {
        try {
            if (cmsSlide != null && cmsSlide.getImage() != null && cmsSlide.getImage().getUrl() != null
                    && !cmsSlide.getImage().getUrl().equalsIgnoreCase("null") && !cmsSlide.getImage().getUrl().equalsIgnoreCase("none")
                    && !cmsSlide.getImage().getUrl().equalsIgnoreCase("")
                    ) {
                String url = "http://api.talentify.in/" + cmsSlide.getImage().getUrl();
                int index = url.lastIndexOf("/");
                String bg_image_name = url.substring(index, url.length()).replace("/", "");
                saveGiforImage(url,bg_image_name);
            }
        }catch (Exception e){

        }

    }

    private void getBackgroundImage(CMSSlide cmsSlide) {
        try {
            if (cmsSlide != null && cmsSlide.getImage_BG() != null && !cmsSlide.getImage_BG().equalsIgnoreCase("none") && cmsSlide.getImage_BG().equalsIgnoreCase("null")) {
                String url = "http://api.talentify.in/" + cmsSlide.getImage_BG();
                int index = url.lastIndexOf("/");
                String bg_image_name = url.substring(index, url.length()).replace("/", "");
                saveGiforImage(url,bg_image_name);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void saveGiforImage(String url,String filename){
        if (url.contains(".gif")) {
            GifImageSaver imageSaver = new GifImageSaver(context).
                    setFileName(filename).
                    setExternal(GifImageSaver.isExternalStorageReadable());
            new SaveGifAsync(imageSaver).execute(url);
        } else {
            ImageSaver imageSaver = new ImageSaver(context).
                    setFileName(filename).
                    setExternal(ImageSaver.isExternalStorageReadable());
            new SaveImageAsync(imageSaver).execute(url);

        }
    }

}
