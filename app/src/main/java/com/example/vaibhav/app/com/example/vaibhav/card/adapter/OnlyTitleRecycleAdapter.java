package com.example.vaibhav.app.com.example.vaibhav.card.adapter;

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
 * Created by Feroz on 21/11/2016.
 */

public class OnlyTitleRecycleAdapter extends RecyclerView.Adapter<OnlyTitleRecycleAdapter.MyViewHolder> {

    private List<String> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

        }
    }


    public OnlyTitleRecycleAdapter(List<String> moviesList) {
        this.moviesList = moviesList;
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
        Spannable spannable = new SpannableString(moviesList.get(position));
        spannable.setSpan(new BulletSpan(60), 0, spannable.length(),
                Spanned.SPAN_USER);
        sb.append(spannable);
        Spannable spannable1 = new SpannableString(moviesList.get(position));

        spannable1.setSpan(new BulletSpan(60), 0, spannable1.length(),
                Spanned.SPAN_PARAGRAPH);
        sb.append(spannable1);

        holder.title.setText(sb);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}