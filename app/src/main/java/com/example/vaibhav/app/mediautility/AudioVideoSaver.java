package com.example.vaibhav.app.mediautility;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by AJITH on 03-11-2016.
 */

public class AudioVideoSaver {
    private String directoryName = "Talentify";
    private String parentDirectory="default";
    private String fileName = "image.mp3";
    private Context context;
    private boolean external;

    public AudioVideoSaver(Context context) {
        this.context = context;
    }

    public AudioVideoSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public AudioVideoSaver setExternal(boolean external) {
        this.external = external;
        return this;
    }

    public AudioVideoSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    public AudioVideoSaver setParentDirectoryName(String parentDirectory) {
        this.parentDirectory=parentDirectory;
        return this;
    }


    public void save(InputStream bitmapImage) {
        FileOutputStream fileOutputStream = null;
        int read = 0;

        try {
            byte[] bytes = new byte[1024];

            fileOutputStream = new FileOutputStream(createFile());
            while ((read = bitmapImage.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @NonNull
    private File createFile() {
        File directory;
        if(external){
            directory = getAlbumStorageDir(directoryName);
        }
        else {
            directory = context.getDir(directoryName, Context.MODE_PRIVATE);
        }

        return new File(directory, fileName);
    }

    private File getAlbumStorageDir(String albumName) {
        File parent_dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!parent_dir.mkdirs()) {

        }
        File file = new File(parent_dir, directoryName+"_"+parentDirectory);

        if(!file.mkdirs()){
            Log.e("Audio Saver", "Directory Already exist File Name : " + fileName);
        }

        return file;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public File load() {
        File file = createFile();
        return file;
    }

    public boolean checkFile() {
        File file = createFile();
        return file.exists();
    }

    public boolean deleteFile(){ File file = createFile(); return file.delete(); }
}