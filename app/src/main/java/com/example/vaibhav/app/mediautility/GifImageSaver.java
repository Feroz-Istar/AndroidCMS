package com.example.vaibhav.app.mediautility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Debug;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Feroz on 31-10-2016.
 */

public class GifImageSaver {

    private String directoryName = "Talentify";
    private String parentDirectory="default";
    private String fileName = "image.png";
    private Context context;
    private boolean external;

    public GifImageSaver(Context context) {
        this.context = context;
    }

    public GifImageSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public GifImageSaver setExternal(boolean external) {
        this.external = external;
        return this;
    }

    public GifImageSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    public GifImageSaver setParentDirectoryName(String parentDirectory) {
        this.parentDirectory=parentDirectory;
        return this;
    }

    public void save(InputStream inputStream) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(createFile());
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] img = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int current = 0;
            Log.d(TAG, "on do in background, write byte to baos");
            while ((current = bis.read()) != -1) {
                baos.write(current);
            }
            fileOutputStream.write(baos.toByteArray());

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
        if (external) {
            directory = getAlbumStorageDir(directoryName);
        } else {
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
            Log.e("GifImageSaver", "Directory Already exist File Name : " + fileName);
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

    public Bitmap load() {

        logHeap();
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
       // options.inPreferredConfig = Bitmap.Config.RGB_565;

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(createFile());
            bitmap = BitmapFactory.decodeStream(inputStream);
            System.gc();
        }catch (OutOfMemoryError oom) {
            System.err.println("File name------------------------>"+this.fileName);
            System.gc();
            bitmap = BitmapFactory.decodeStream(inputStream,null,options);

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    public boolean checkFile() {
        File file = createFile();
        return file.exists();
    }

    public File pathFile(){
        File file = createFile();
        return file;
    }

    public boolean deleteFile() {
        File file = createFile();
        return file.delete();
    }


    public static void logHeap() {
        Double allocated = new Double(Debug.getNativeHeapAllocatedSize())/new Double((1048576));
        Double available = new Double(Debug.getNativeHeapSize())/1048576.0;
        Double free = new Double(Debug.getNativeHeapFreeSize())/1048576.0;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

      //  Log.d("talentify", "debug. =================================");
       // Log.d("talentify", "debug.heap native: allocated " + df.format(allocated) + "MB of " + df.format(available) + "MB (" + df.format(free) + "MB free)");
       // Log.d("talentify", "debug.memory: allocated: " + df.format(new Double(Runtime.getRuntime().totalMemory()/1048576)) + "MB of " + df.format(new Double(Runtime.getRuntime().maxMemory()/1048576))+ "MB (" + df.format(new Double(Runtime.getRuntime().freeMemory()/1048576)) +"MB free)");
    }



}