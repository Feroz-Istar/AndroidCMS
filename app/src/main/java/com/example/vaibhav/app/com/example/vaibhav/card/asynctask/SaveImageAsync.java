package com.example.vaibhav.app.com.example.vaibhav.card.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.vaibhav.app.mediautility.ImageSaver;

import java.net.URL;

/**
 * Created by Feroz on 24/11/2016.
 */

public class SaveImageAsync extends AsyncTask<String, Void, Bitmap> {
    private ImageSaver imageSaver;

    public SaveImageAsync(ImageSaver imageSaver){
        this.imageSaver = imageSaver;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bmp = null;
        try{
        URL url = new URL(params[0]);
            bmp   = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        }catch (Exception e){
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null)
        imageSaver.save(bitmap);

    }
}
