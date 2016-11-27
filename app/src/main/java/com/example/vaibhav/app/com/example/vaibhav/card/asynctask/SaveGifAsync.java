package com.example.vaibhav.app.com.example.vaibhav.card.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.vaibhav.app.mediautility.GifImageSaver;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Feroz on 27/11/2016.
 */

public class SaveGifAsync extends AsyncTask<String, Void, Bitmap> {
    private GifImageSaver imageSaver;

    public SaveGifAsync(GifImageSaver imageSaver){
        this.imageSaver = imageSaver;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bmp = null;
        try{
            URL url = new URL(params[0].replace(" ", "%20"));
            InputStream fileInputStream = url.openStream();
            imageSaver.save(fileInputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {


    }
}