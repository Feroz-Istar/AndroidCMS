package com.example.vaibhav.app.com.example.vaibhav.card.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.cmspojo.CMSTextItem;
import com.example.vaibhav.app.util.BulletListBuilder;

import java.util.ArrayList;

/**
 * Created by Feroz on 21/11/2016.
 */

public class OnlyTitleTreeRecycleAdapter extends RecyclerView.Adapter<OnlyTitleTreeRecycleAdapter.MyViewHolder> {

    private CMSSlide cmsSlide;
    private  Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1,title2;

        public MyViewHolder(View view) {
            super(view);
            title1 = (TextView) view.findViewById(R.id.title1);
            title2 = (TextView) view.findViewById(R.id.title2);
        }
    }


    public OnlyTitleTreeRecycleAdapter(CMSSlide cmsSlide , Context context) {

        this.cmsSlide = cmsSlide;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_tree_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String listitems = cmsSlide.getList().getItems().get(position).getText();
        ArrayList<String> bulletList = new ArrayList<>();

        for(CMSTextItem list : cmsSlide.getList().getItems().get(position).getList().getItems()){
            if(list != null && list.getText() != null)
                bulletList.add(list.getText());

        }

        Typeface paragraphtf = Typeface.createFromAsset(context.getAssets(), "Raleway-Regular.ttf");

        holder.title1.setTypeface(paragraphtf);
        holder.title1.setTextColor(Color.parseColor(cmsSlide.getTheme().getParagraphFontColor()));
        holder.title1.setTextSize(Integer.parseInt(cmsSlide.getTheme().getParagraphFontSize()) / 3);
        holder.title1.setText(new BulletListBuilder(context).getBulletListTitle(listitems,"",15));
        //holder.title1.setText(listitems);

        if(bulletList.size() > 0)

        holder.title2.setTypeface(paragraphtf);
        holder.title2.setTextColor(Color.parseColor(cmsSlide.getTheme().getParagraphFontColor()));
        holder.title2.setTextSize(Integer.parseInt(cmsSlide.getTheme().getParagraphFontSize()) / 3);

        holder.title2.setText(new BulletListBuilder(context).getBulletList(bulletList,"",15));

    }

    @Override
    public int getItemCount() {
        return cmsSlide.getList().getItems().size();
    }
}