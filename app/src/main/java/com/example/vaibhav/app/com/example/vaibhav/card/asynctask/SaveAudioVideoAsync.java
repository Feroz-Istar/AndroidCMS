package com.example.vaibhav.app.com.example.vaibhav.card.asynctask;

import android.os.AsyncTask;

import com.example.vaibhav.app.mediautility.AudioVideoSaver;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Feroz on 24/11/2016.
 */

public class SaveAudioVideoAsync extends AsyncTask<String, Void, InputStream> {
    private AudioVideoSaver audioVideoSaver;

    public SaveAudioVideoAsync(AudioVideoSaver audioVideoSaver){
        this.audioVideoSaver = audioVideoSaver;
    }
    @Override
    protected InputStream doInBackground(String... params) {
        InputStream fileInputStream = null;
        try {
            fileInputStream  = new URL(params[0].replace(" ", "%20")).openStream();
            audioVideoSaver.save(fileInputStream);

        }catch (Exception e){
            e.printStackTrace();
        }
        return fileInputStream;
    }

    @Override
    protected void onPostExecute(InputStream fileInputStream) {

    }
}