package com.example.vaibhav.app.com.example.vaibhav.card.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.vaibhav.app.mediautility.ImageSaver;

/**
 * Created by Feroz on 26/11/2016.
 */

public class LoadImageAsync extends AsyncTask<String, Void, Bitmap> {
    private ImageSaver imageSaver;
    private ImageView imageView;

    public LoadImageAsync(ImageSaver imageSaver,ImageView imageView){
        this.imageSaver = imageSaver;
        this.imageView = imageView;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = imageSaver.setExternal(ImageSaver.isExternalStorageReadable()).load();

        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
