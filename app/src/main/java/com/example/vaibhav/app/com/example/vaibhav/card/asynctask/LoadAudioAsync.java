package com.example.vaibhav.app.com.example.vaibhav.card.asynctask;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.vaibhav.app.mediautility.AudioVideoSaver;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Feroz on 26/11/2016.
 */

public class LoadAudioAsync extends AsyncTask<String, Void, Uri> {
    private AudioVideoSaver audioVideoSaver;
    private MediaPlayer mediaPlayer;
    private Context context;
    public LoadAudioAsync(AudioVideoSaver audioVideoSaver, MediaPlayer mediaPlayer, Context context){
        this.audioVideoSaver = audioVideoSaver;
        this.mediaPlayer = mediaPlayer;
        this.context = context;
    }
    @Override
    protected Uri doInBackground(String... params) {
        InputStream fileInputStream = null;
        Uri uri = null;

        try {
            fileInputStream  = new URL(params[0].replace(" ", "%20")).openStream();
            audioVideoSaver.save(fileInputStream);
            uri = Uri.parse(params[0].replace(" ", "%20"));

        }catch (Exception e){
            e.printStackTrace();
        }
        return uri;
    }

    @Override
    protected void onPostExecute(Uri fileInputStream) {
        try {
            mediaPlayer.setDataSource(context,fileInputStream);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}