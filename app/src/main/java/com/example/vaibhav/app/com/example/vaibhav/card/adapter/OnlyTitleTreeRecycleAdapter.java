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
import com.example.vaibhav.app.cmspojo.CMSList;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.cmspojo.CMSTextItem;
import com.example.vaibhav.app.util.BulletListBuilder;

import java.util.ArrayList;
import java.util.List;

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
        holder.title1.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        holder.title2.setTextColor(context.getResources().getColor(R.color.colorPrimary));


        holder.title1.setText(listitems);
        if(bulletList.size() > 0)
            holder.title2.setText(new BulletListBuilder(context).getBulletList(bulletList));



    }

    @Override
    public int getItemCount() {
        return cmsSlide.getList().getItems().size();
    }
}