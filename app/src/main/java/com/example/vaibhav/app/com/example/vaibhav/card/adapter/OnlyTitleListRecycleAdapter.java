package com.example.vaibhav.app.com.example.vaibhav.card.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;

/**
 * Created by Sumanth on 11/23/2016.
 */





public class OnlyTitleListRecycleAdapter extends RecyclerView.Adapter<OnlyTitleListRecycleAdapter.MyViewHolder> {

    private CMSSlide cmsSlide;
    private  Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title ;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

        }
    }


    public OnlyTitleListRecycleAdapter(CMSSlide cmsSlide, Context context) {
        this.cmsSlide = cmsSlide;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        Spannable spannable = null;


       /* if(cmsSlide.getList().getList_type().equalsIgnoreCase("SIMPLE_LIST")){

             spannable = new SpannableString(cmsSlide.getList().getItems().get(position).getText());
            spannable.setSpan(new BulletSpan(60), 0, spannable.length(),Spanned.SPAN_USER);
        }
        if(cmsSlide.getList().getList_type().equalsIgnoreCase("IN_OUT_1")){

             spannable = new SpannableString(cmsSlide.getList().getItems().get(position).getText());
            spannable.setSpan(new BulletSpan(60), 0, spannable.length(),Spanned.SPAN_USER);
        }
        if(cmsSlide.getList().getList_type().equalsIgnoreCase("IN_OUT_2")){

             spannable = new SpannableString(cmsSlide.getList().getItems().get(position).getText());
            spannable.setSpan(new BulletSpan(60), 0, spannable.length(),Spanned.SPAN_USER);
        }
        if(cmsSlide.getList().getList_type().equalsIgnoreCase("TWO_LIST")){

             spannable = new SpannableString(cmsSlide.getList().getItems().get(position).getText());
            spannable.setSpan(new BulletSpan(60), 0, spannable.length(),Spanned.SPAN_USER);
        }*/






        spannable = new SpannableString(cmsSlide.getList().getItems().get(position).getText());
        spannable.setSpan(new BulletSpan(60), 0, spannable.length(), Spanned.SPAN_USER);

        sb.append(spannable);
        Typeface descriptiontf = Typeface.createFromAsset(context.getAssets(),"Raleway-Thin.ttf");
        holder.title.setTypeface(descriptiontf);
        holder.title.setTextColor(Color.parseColor(cmsSlide.getTheme().getTitleFontColor()));
        holder.title.setTextSize(Integer.parseInt(cmsSlide.getTheme().getTitleFontSize())/5);
        holder.title.setText(sb);

    }

    @Override
    public int getItemCount() {
        return cmsSlide.getList().getItems().size();
    }
}