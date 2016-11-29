package com.example.vaibhav.app.util;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.example.vaibhav.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 20/11/2016.
 */

public class BulletListBuilder {
    private SpannableStringBuilder sb;
    private static final String SPACE = " ";
    private static final String BULLET_SYMBOL = "&#x25cf;&nbsp;&nbsp;&nbsp;";
    private static final String EOL = System.getProperty("line.separator");
    private static final String TAB = "\t";
    private static final String MUTABLE_FOREGROUND_COLOR_SPAN_FC_PROPERTY="#000";
    private Context context;
    public BulletListBuilder(Context context) {
    this.context = context;
    }
    public CharSequence getBulletListTitle ( String items, String bullet,int spacing) {

        List<Spanned> spanned = new ArrayList<>(1);
        if (!items.trim().isEmpty()) {
            Spanned spannedLine = Html.fromHtml(BULLET_SYMBOL+items.trim());
            spanned.add(spannedLine);
        }

        sb = new SpannableStringBuilder();

        for (int i = 0; i < spanned.size(); i++) {
            CharSequence line = spanned.get(i) + (i < spanned.size() - 1 ? "\n" : "");
            Spannable spannable = new SpannableString(line);

            sb.append(spannable);
        }
        return sb;
    }

    public CharSequence getBulletList( List<String> items, String bullet,int spacing) {

        List<Spanned> spanned = new ArrayList<>(items.size());
        for (String line : items) {
            if (line != null && !line.trim().isEmpty()) {
                Spanned spannedLine = Html.fromHtml(BULLET_SYMBOL+line.trim());
                spanned.add(spannedLine);
            }
        }

         sb = new SpannableStringBuilder();

        for (int i = 0; i < spanned.size(); i++) {
            CharSequence line = spanned.get(i) + (i < spanned.size() - 1 ? "\n" : "");
            Spannable spannable = new SpannableString(line+" \n");

            sb.append(spannable);
        }
        return sb;
    }

    public CharSequence getNumberList( List<String> items, String bullet,int spacing) {

        List<Spanned> spanned = new ArrayList<>(items.size());
        for (String line : items) {
            if (!line.trim().isEmpty()) {
                Spanned spannedLine = Html.fromHtml(line.trim());
                spanned.add(spannedLine);
            }
        }

        sb = new SpannableStringBuilder();

        for (int i = 0; i < spanned.size(); i++) {
            CharSequence line = spanned.get(i) + (i < spanned.size() - 1 ? "\n" : "");
            Spannable spannable = new SpannableString(line);
            spannable.setSpan(new NumberIndentSpan(15, 15,i+1), 0, spannable.length(),
                    Spanned.SPAN_USER);
            sb.append(spannable);
        }
        return sb;
    }




    class MyClickableSpan extends ClickableSpan {
        public MyClickableSpan(String string) {
            super();

        }

        public void onClick(View tv) {

            Toast.makeText(context, "Thanks for the click!",
                    Toast.LENGTH_SHORT).show();
        }

        public void updateDrawState(TextPaint ds) {

            ds.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
            ds.setUnderlineText(false); // set to false to remove underline
        }


    }



}