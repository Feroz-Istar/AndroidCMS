package com.example.vaibhav.app.com.example.vaibhav.card;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.cmspojo.CMSTextItem;
import com.example.vaibhav.app.cmspojo.cmstablepojo.Table;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveAudioVideoAsync;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveGifAsync;
import com.example.vaibhav.app.com.example.vaibhav.card.asynctask.SaveImageAsync;
import com.example.vaibhav.app.mediautility.AudioVideoSaver;
import com.example.vaibhav.app.mediautility.GifImageSaver;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.BulletListBuilder;
import com.example.vaibhav.app.util.CustomLayout;
import com.example.vaibhav.app.viewpager.SampleActivity;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Feroz on 24/11/2016.
 */

public class ThemeUtils {

    public void massageTitle(CMSSlide cms, TextView title, Context context) {
        Typeface titletf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");

        if (cms.getTitle() != null) {
            if (cms.getTitle().getText() != null) {
                title.setText(cms.getTitle().getText());
                title.setTypeface(titletf, Typeface.BOLD);
            }
            if (cms.getTheme() != null && cms.getTheme().getTitleFontColor() != null) {
                title.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
                title.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize()) / 3);
            }

            /*if (cms.getTitle().getFragmentAudioUrl() != null) {
                String url = "http://api.talentify.in/video/audio/" + cms.getTitle().getFragmentAudioUrl();
                int index = url.lastIndexOf("/");
                String audio_nasetFileNameme = url.substring(index, url.length()).replace("/", "");
                AudioVideoSaver audioVideoSaver = new AudioVideoSaver(context).
                        (audio_name).
                        setExternal(ImageSaver.isExternalStorageReadable());
                Boolean file_exist = audioVideoSaver.checkFile();
                Uri videouri = null;
                if (file_exist) {
                    System.out.println("audio  just play it");
                    videouri = Uri.fromFile(audioVideoSaver.load());
                } else {
                    try {
                        videouri = Uri.parse(url);
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                    new SaveAudioVideoAsync(audioVideoSaver).execute(url);
                }
                try{
                    mediaPlayer.setDataSource(context,videouri);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    *//*mediaPlayer.prepare();
                    mediaPlayer.start();*//*
                }catch (Exception e){

                }

            }*/
        }
    }

    public void massageTitle2(CMSSlide cms, TextView title, Context context) {
        Typeface titletf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");
        if (cms.getTitle2() != null && cms.getTitle2().getText() != null) {
            title.setText(cms.getTitle2().getText());
            title.setTypeface(titletf,Typeface.BOLD);
        }
        if (cms.getTheme() != null && cms.getTheme().getTitleFontColor() != null) {
            title.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
            title.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize()) / 3);
        }

    }


    public void massageParagraph(CMSSlide cms, TextView paragraph, Context context) {
        Typeface paragraphtf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");

        if (cms.getParagraph() != null && cms.getParagraph().getText() != null) {
            System.out.println("--------------------->" + cms.getParagraph().getText());
            paragraph.setText(Html.fromHtml(cms.getParagraph().getText()));
            paragraph.setTypeface(paragraphtf);
            paragraph.setTextColor(Color.parseColor(cms.getTheme().getParagraphFontColor()));
            paragraph.setTextSize(Integer.parseInt(cms.getTheme().getParagraphFontSize()) / 3);

        }
    }

    public void massageParagraph(CMSSlide cms, WebView paragraph, Context context) {
        Typeface paragraphtf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");

        if (cms.getParagraph() != null && cms.getParagraph().getText() != null) {
            System.out.println("--------------------->" + cms.getParagraph().getText());
            paragraph.loadDataWithBaseURL(null, cms.getParagraph().getText(), "text/html", "utf-8", null);
            //  paragraph.setTypeface(paragraphtf);
            //  paragraph.setTextColor(Color.parseColor(cms.getTheme().getParagraphFontColor()));
            //  paragraph.setTextSize(Integer.parseInt(cms.getTheme().getParagraphFontSize()) / 3);

        }
    }

    public void massageBackgroundLayout(CMSSlide cms, Picasso mPicasso, CustomLayout main_layout, Boolean externalReadable, Context context, GifImageView gifImageView) {
        if (cms.getBackground() != null) {
            if (!cms.getBackground().equalsIgnoreCase("#ffffff") && !cms.getBackground().equalsIgnoreCase("#000000") && !cms.getBackground().equalsIgnoreCase("none") && !cms.getBackground().equalsIgnoreCase("null")) {
                main_layout.setBackgroundColor(Color.parseColor(cms.getBackground()));
            } else {
                if (cms.getTheme() != null && cms.getTheme().getBackgroundColor() != null)
                    main_layout.setBackgroundColor(Color.parseColor(cms.getTheme().getBackgroundColor()));
            }
        }

        if (cms.getImage_BG() != null && !cms.getImage_BG().equalsIgnoreCase("none") && !cms.getImage_BG().equalsIgnoreCase("null")) {
            int index = cms.getImage_BG().lastIndexOf("/");
            String url = "http://api.talentify.in" + cms.getImage_BG();
            System.out.println("cms.getImage_BG() " + cms.getImage_BG() + " index " + index);
            if (cms.getImage_BG().contains(".gif")) {
                checkGIFImage(url, context, gifImageView);
            } else {
                String bg_image_name = cms.getImage_BG().substring(index, cms.getImage_BG().length()).replace("/", "");
                ImageSaver imageSaver = new ImageSaver(context).
                        setParentDirectoryName("" + SampleActivity.ppt_id).
                        setFileName(bg_image_name).
                        setExternal(externalReadable);
                Boolean file_exist = imageSaver.checkFile();


                if (file_exist) {
                    Bitmap bitmap = imageSaver.setExternal(externalReadable).load();
                    BitmapDrawable background = new BitmapDrawable(bitmap);
                    main_layout.setBackgroundDrawable(background);
                } else {
                    mPicasso.load(url).into(main_layout);
                    new SaveImageAsync(imageSaver).execute(url);
                }
            }
        }
    }


    public void massageImage(String url, Picasso mPicasso, ImageView imageView, Boolean externalReadable, Context context, GifImageView gifImageView) {
        int index = url.lastIndexOf("/");
        String bg_image_name = url.substring(index, url.length()).replace("/", "");
        if (url.contains(".gif")) {
            imageView.setVisibility(View.GONE);
            checkGIFImage(url, context, gifImageView);
        } else {

            ImageSaver imageSaver = new ImageSaver(context).
                    setParentDirectoryName("" + SampleActivity.ppt_id).
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
    }

    public void massageVideo(CMSSlide cms, final VideoView video, Boolean externalReadable, Context context) {
        if (cms.getVideo() != null && cms.getVideo().getUrl() != null) {
            String url = "http://api.talentify.in/" + cms.getVideo().getUrl();
            int index = url.lastIndexOf("/");
            String bg_image_name = url.substring(index, url.length()).replace("/", "");
            AudioVideoSaver audioVideoSaver = new AudioVideoSaver(context).
                    setParentDirectoryName("" + SampleActivity.ppt_id).
                    setFileName(bg_image_name + ".mp4").
                    setExternal(externalReadable);
            Boolean file_exist = audioVideoSaver.checkFile();
            Uri videouri = null;
            if (file_exist) {
                System.out.println("VIdeo existr just play it");
                videouri = Uri.fromFile(audioVideoSaver.load());
            } else {
                try {
                    videouri = Uri.parse(url);
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

    public void massageList(CMSSlide cms, TextView paragraph, Context context) {
        Typeface paragraphtf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");
        try {
            if (cms.getList() != null && cms.getList().getItems() != null) {
                SpannableStringBuilder sb = new SpannableStringBuilder();
                List<String> lines = new ArrayList<>();
                for (CMSTextItem item : cms.getList().getItems()) {
                    if (item.getText() != null) {
                        lines.add(item.getText().replaceAll("<p>",""));
                    } else {
                        lines.remove(item);
                    }
                }
                paragraph.setText(new BulletListBuilder(context).getBulletList(lines, "", 15));
                paragraph.setTypeface(paragraphtf);
                paragraph.setTextColor(Color.parseColor(cms.getTheme().getListitemFontColor()));
                paragraph.setTextSize((float) (Integer.parseInt(cms.getTheme().getListitemFontSize()) / 3.0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void massageTreeList(CMSSlide cms, TextView paragraph, Context context) {


    }

    public void massageNumberList(CMSSlide cms, TextView paragraph, Context context) {
        Typeface paragraphtf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");
        if (cms.getList() != null && cms.getList().getItems() != null) {
            SpannableStringBuilder sb = new SpannableStringBuilder();
            List<String> lines = new ArrayList<>();
            for (CMSTextItem item : cms.getList().getItems()) {
                lines.add(item.getText());
            }
            paragraph.setText(new BulletListBuilder(context).getNumberList(lines, "", 15));
            paragraph.setTypeface(paragraphtf);
            paragraph.setTextColor(Color.parseColor(cms.getTheme().getListitemFontColor()));
            paragraph.setTextSize((float) (Integer.parseInt(cms.getTheme().getListitemFontSize()) / 2.5));

        }
    }

    private void checkGIFImage(String url, Context context, GifImageView gifImageView) {
        int index = url.lastIndexOf("/");
        String bg_image_name = url.substring(index, url.length()).replace("/", "");
        GifImageSaver imageSaver = new GifImageSaver(context).
                setParentDirectoryName("" + SampleActivity.ppt_id).
                setFileName(bg_image_name).
                setExternal(GifImageSaver.isExternalStorageReadable());
        Boolean file_exist = imageSaver.checkFile();
        gifImageView.setVisibility(View.VISIBLE);

        if (file_exist) {
            try {
                GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView);
                Glide.with(context).load(imageSaver.pathFile()).into(imageViewTarget);

                //gifImageView.setImageDrawable(new GifDrawable(imageSaver.pathFile()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            Glide.with(context).load(url).listener(new LoggingListener<String, GlideDrawable>()).diskCacheStrategy(DiskCacheStrategy.NONE).error(context.getResources().getDrawable(R.mipmap.ic_launcher)).into(gifImageView);
            new SaveGifAsync(imageSaver).execute(url);

        }
    }

    public class LoggingListener<T, R> implements RequestListener<T, R> {
        @Override
        public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
            android.util.Log.d("GLIDE", String.format(Locale.ROOT,
                    "onException(%s, %s, %s, %s)", e, model, target, isFirstResource), e);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
            android.util.Log.d("GLIDE", String.format(Locale.ROOT,
                    "onResourceReady(%s, %s, %s, %s, %s)", resource, model, target, isFromMemoryCache, isFirstResource));
            return false;
        }
    }

    public void massageTable(CMSSlide cms, TableLayout table_main, TextView paragraph, Context context) {

        Typeface paragraphtf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");
        Table TableData = null;
        Elements elements, elements1;
        if (cms.getParagraph() != null && cms.getParagraph().getText() != null) {
            Document doc = Jsoup.parse(cms.getParagraph().getText());
            elements = doc.select("p");
            elements1 = doc.select("table");


            if (elements.size() > 0 && elements1.size() > 0) {
                paragraph.setVisibility(View.VISIBLE);
                table_main.setVisibility(View.VISIBLE);

                paragraph.setText(Html.fromHtml(elements.text()));
                paragraph.setTypeface(paragraphtf);
                paragraph.setTextColor(Color.parseColor(cms.getTheme().getParagraphFontColor()));
                paragraph.setTextSize(Integer.parseInt(cms.getTheme().getParagraphFontSize()) / 3);

                Serializer serializer = new Persister();
                try {
                    TableData = serializer.read(Table.class, String.valueOf(elements1), false);

                    TableRow tbrow = new TableRow(context);
                    int head_count =0;
                    if(TableData != null) {
                        if (TableData.getThead() != null && TableData.getThead().getTr() != null && TableData.getThead().getTr().getTh() != null && TableData.getThead().getTr().getTh().size() > 0) {

                            for (int i = 0; i < TableData.getThead().getTr().getTh().size(); i++) {
                                TextView THeadData = new TextView(context);
                                String ThData = TableData.getThead().getTr().getTh().get(i).getContent();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                    THeadData.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                }

                                THeadData.setText(ThData);
                                THeadData.setTextColor(Color.BLACK);
                                THeadData.setTypeface(Typeface.DEFAULT_BOLD);
                                GradientDrawable gd = (GradientDrawable) context.getResources().getDrawable(R.drawable.cellborder);
                                gd.setColor(Color.parseColor("#DCDCDC"));
                                THeadData.setBackground(gd);
                                THeadData.setTextSize(10);

                                head_count++;
                                tbrow.addView(THeadData);

                            }
                            table_main.addView(tbrow);
                        }
                        if (TableData.getTbody() != null && TableData.getTbody().getTr() != null && TableData.getTbody().getTr().size() > 0) {
                            for (int j = 0; j < TableData.getTbody().getTr().size(); j++) {

                                TableRow tbrow1 = new TableRow(context);
                                if (TableData.getTbody().getTr().get(j).getTh() != null) {

                                    for (int l = 0; l < TableData.getTbody().getTr().get(j).getTh().size(); l++) {

                                        TextView TBodyData = new TextView(context);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                            TBodyData.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                        }
                                        String ThData = TableData.getTbody().getTr().get(j).getTd().get(l).getContent();
                                        TBodyData.setText(ThData);
                                        if (head_count < 4) {
                                            TBodyData.setTextSize(10);
                                        } else {
                                            TBodyData.setTextSize(8);

                                        }
                                        TBodyData.setTextColor(Color.BLACK);
                                        TBodyData.setTypeface(Typeface.DEFAULT_BOLD);
                                        GradientDrawable gd = (GradientDrawable) context.getResources().getDrawable(R.drawable.cellborder);
                                        gd.setColor(Color.parseColor("#DCDCDC"));
                                        TBodyData.setBackground(gd);
                                        tbrow1.addView(TBodyData);

                                    }
                                }
                                for (int k = 0; k < TableData.getTbody().getTr().get(j).getTd().size(); k++) {

                                    TextView TBodyData = new TextView(context);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                        TBodyData.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    }
                                    String TbData = TableData.getTbody().getTr().get(j).getTd().get(k).getContent();
                                    TBodyData.setText(TbData);
                                    if (head_count < 4) {
                                        TBodyData.setTextSize(10);
                                    } else {
                                        TBodyData.setTextSize(8);

                                    }
                                    TBodyData.setTextColor(Color.BLACK);
                                    GradientDrawable gd = (GradientDrawable) context.getResources().getDrawable(R.drawable.cellborder);
                                    gd.setColor(Color.WHITE);
                                    TBodyData.setBackground(gd);
                                    tbrow1.addView(TBodyData);

                                }
                                table_main.addView(tbrow1);

                            }
                        }
                    }


                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else if (elements.size() > 0) {

                paragraph.setVisibility(View.VISIBLE);
                table_main.setVisibility(View.GONE);

                paragraph.setText(Html.fromHtml(elements.text()));
                paragraph.setTypeface(paragraphtf);
                paragraph.setTextColor(Color.parseColor(cms.getTheme().getParagraphFontColor()));
                paragraph.setTextSize(Integer.parseInt(cms.getTheme().getParagraphFontSize()) / 3);


            } else if (elements1.size() > 0) {

                table_main.setVisibility(View.VISIBLE);
                paragraph.setVisibility(View.GONE);

                Serializer serializer = new Persister();
                try {
                    TableData = serializer.read(Table.class, String.valueOf(elements1), false);


                    TableRow tbrow = new TableRow(context);

                        if(TableData != null) {
                            int head_count = 0;
                            if (TableData.getThead() != null && TableData.getThead().getTr() != null) {
                                if (TableData.getThead().getTr().getTh() != null && TableData.getThead().getTr().getTh().size() > 0) {



                                for (int i = 0; i < TableData.getThead().getTr().getTh().size(); i++) {
                                    TextView THeadData = new TextView(context);
                                    String ThData = TableData.getThead().getTr().getTh().get(i).getContent();
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                        THeadData.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    }

                                    THeadData.setText(ThData);
                                    THeadData.setTextColor(Color.BLACK);
                                    THeadData.setTypeface(Typeface.DEFAULT_BOLD);
                                    GradientDrawable gd = (GradientDrawable) context.getResources().getDrawable(R.drawable.cellborder);
                                    gd.setColor(Color.parseColor("#DCDCDC"));
                                    THeadData.setBackground(gd);
                                    THeadData.setTextSize(10);

                                    head_count++;
                                    tbrow.addView(THeadData);

                                }
                                table_main.addView(tbrow);
                            }
                            }
                            if (TableData.getTbody() != null && TableData.getTbody().getTr() != null && TableData.getTbody().getTr().size() > 0) {
                                for (int j = 0; j < TableData.getTbody().getTr().size(); j++) {

                                    TableRow tbrow1 = new TableRow(context);
                                    if (TableData.getTbody().getTr().get(j).getTh() != null) {

                                        for (int l = 0; l < TableData.getTbody().getTr().get(j).getTh().size(); l++) {
                                            TextView TBodyData = new TextView(context);
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                                TBodyData.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                            }
                                            String ThData = TableData.getTbody().getTr().get(j).getTh().get(l).getContent();
                                            TBodyData.setText(ThData);
                                            if (head_count < 4) {
                                                TBodyData.setTextSize(10);
                                            } else {
                                                TBodyData.setTextSize(8);

                                            }
                                            TBodyData.setTextColor(Color.BLACK);
                                            TBodyData.setTypeface(Typeface.DEFAULT_BOLD);
                                            GradientDrawable gd = (GradientDrawable) context.getResources().getDrawable(R.drawable.cellborder);
                                            gd.setColor(Color.parseColor("#DCDCDC"));
                                            TBodyData.setBackground(gd);
                                            tbrow1.addView(TBodyData);


                                        }

                                    }
                                    if (TableData.getTbody().getTr().get(j).getTd() != null){
                                    for (int k = 0; k < TableData.getTbody().getTr().get(j).getTd().size(); k++) {

                                        TextView TBodyData = new TextView(context);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                            TBodyData.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                        }

                                            String TbData = TableData.getTbody().getTr().get(j).getTd().get(k).getContent();
                                        TBodyData.setText(TbData);
                                        if (head_count < 4) {
                                            TBodyData.setTextSize(10);
                                        } else {
                                            TBodyData.setTextSize(8);

                                        }
                                        TBodyData.setTextColor(Color.BLACK);
                                        GradientDrawable gd = (GradientDrawable) context.getResources().getDrawable(R.drawable.cellborder);
                                        gd.setColor(Color.WHITE);
                                        TBodyData.setBackground(gd);
                                        tbrow1.addView(TBodyData);
                                    }
                                    }
                                    table_main.addView(tbrow1);

                                }
                            }
                        }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }


    }

    public void message2Box(CMSSlide cms, TextView list1,TextView list2, Context context) {

        Typeface paragraphtf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");
        try {
            if (cms.getList() != null && cms.getList().getItems() != null ) {
                SpannableStringBuilder sb = new SpannableStringBuilder();
                List<String> lines = new ArrayList<>();
                for (CMSTextItem item : cms.getList().getItems()) {
                    if(item.getText() != null){
                        lines.add(item.getText().replaceAll("<p>",""));
                    }else{  lines.remove(item); }
                }
                list1.setText(lines.get(0));
                list1.setTypeface(paragraphtf);
                list1.setTextColor(Color.parseColor(cms.getTheme().getListitemFontColor()));
                list1.setTextSize((float) (Integer.parseInt(cms.getTheme().getListitemFontSize()) / 2.5));
                list2.setText(lines.get(1));
                list2.setTypeface(paragraphtf);
                list2.setTextColor(Color.parseColor(cms.getTheme().getListitemFontColor()));
                list2.setTextSize((float) (Integer.parseInt(cms.getTheme().getListitemFontSize()) / 2.5));
            }
        }catch (Exception e){

        }

    }

}