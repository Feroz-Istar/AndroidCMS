package com.example.vaibhav.app.com.example.vaibhav.card.adapter;

import android.content.Context;
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

import java.util.List;
/**
 * Created by Sumanth on 11/23/2016.
 */





public class OnlyListRecycleAdapter extends RecyclerView.Adapter<OnlyListRecycleAdapter.MyViewHolder> {

    private List<String> listItems;
    private  Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title ;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

        }
    }


    public OnlyListRecycleAdapter(List<String> listItems, Context context) {
        this.listItems = listItems;
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
        Spannable spannable = new SpannableString(listItems.get(position));
        spannable.setSpan(new BulletSpan(60), 0, spannable.length(),
                Spanned.SPAN_USER);
        sb.append(spannable);
        Spannable spannable1 = new SpannableString(listItems.get(position));

        spannable1.setSpan(new BulletSpan(60), 0, spannable1.length(),
                Spanned.SPAN_PARAGRAPH);
        sb.append(spannable1);

        holder.title.setTextColor(context.getResources().getColor(R.color.colorPrimary));

        holder.title.setText(sb);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}