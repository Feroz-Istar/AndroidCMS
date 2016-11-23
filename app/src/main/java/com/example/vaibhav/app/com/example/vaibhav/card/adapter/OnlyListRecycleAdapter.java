package com.example.vaibhav.app.com.example.vaibhav.card.adapter;

import android.content.Context;
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
import com.example.vaibhav.app.util.NumberIndentSpan;
/**
 * Created by Sumanth on 11/23/2016.
 */





public class OnlyListRecycleAdapter extends RecyclerView.Adapter<OnlyListRecycleAdapter.MyViewHolder> {

    private CMSSlide cmsSlide;
    private  Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title ;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

        }
    }


    public OnlyListRecycleAdapter(CMSSlide cmsSlide, Context context) {
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
        Spannable spannable = new SpannableString(cmsSlide.getList().getItems().get(position).getText());

        if(cmsSlide.getTemplateName().equalsIgnoreCase("ONLY_TITLE_LIST_NUMBERED") || cmsSlide.getTemplateName().equalsIgnoreCase("ONLY_LIST_NUMBERED")){
            spannable.setSpan(new NumberIndentSpan(60, 60,position), 0, spannable.length(),
                    Spanned.SPAN_USER);
        }else {
            spannable.setSpan(new BulletSpan(60), 0, spannable.length(),
                    Spanned.SPAN_USER);
        }

        sb.append(spannable);


        holder.title.setTextColor(context.getResources().getColor(R.color.colorPrimary));

        holder.title.setText(sb);
        Typeface descriptiontf = Typeface.createFromAsset(context.getAssets(),"Raleway-Regular.ttf");
        holder.title.setTypeface(descriptiontf);
    }

    @Override
    public int getItemCount() {
        return cmsSlide.getList().getItems().size();
    }
}