package com.example.vaibhav.app.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

/**
 * Created by Feroz on 23/11/2016.
 */

public  class NumberIndentSpan implements LeadingMarginSpan {

    private final int gapWidth;
    private final int leadWidth;
    private final int index;

    public NumberIndentSpan(int leadGap, int gapWidth, int index) {
        this.leadWidth = leadGap;
        this.gapWidth = gapWidth;
        this.index = index;
    }

    public int getLeadingMargin(boolean first) {
        return leadWidth + gapWidth;
    }

    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout l) {
        if (first) {
            Paint.Style orgStyle = p.getStyle();
            p.setStyle(Paint.Style.FILL);
            float width = p.measureText("4.");
            c.drawText(index + ".", (leadWidth + x - width / 2) * dir, bottom - p.descent(), p);
            p.setStyle(orgStyle);
        }
    }
}