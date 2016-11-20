package com.example.vaibhav.app.com.example.vaibhav.card;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.BulletListUtil;
import com.example.vaibhav.app.MutableForegroundColorSpan;
import com.example.vaibhav.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 19/11/2016.
 */

public class ONLYTITLELIST extends Card {
    private TextView list;
    SpannableString string;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_list, container, false);
        list = (TextView) view.findViewById(R.id.list);

        List<String> lines = new ArrayList<>();
        lines.add("First line");
        lines.add("Second line");
        lines.add("Really long third line that will wrap and indent properly.");
        lines.add("FOurth");
        lines.add("Really long third line that will wrap and indent properly.");


        CharSequence bulletedList = BulletListUtil.makeBulletList(lines,30);
        list.setText(bulletedList);
        MutableForegroundColorSpan span = new MutableForegroundColorSpan(255, Color.BLACK);
        string = new SpannableString(list.getText());
        string.setSpan(span, 0, list.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(span, "#000", Color.BLACK, Color.RED);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //refresh
                list.setText(string);
            }
        });
        objectAnimator.start();
        return view ;
    }


}
