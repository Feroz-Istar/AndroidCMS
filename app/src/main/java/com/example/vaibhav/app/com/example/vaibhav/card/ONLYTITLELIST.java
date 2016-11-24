package com.example.vaibhav.app.com.example.vaibhav.card;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.com.example.vaibhav.card.adapter.OnlyListRecycleAdapter;

/**
 * Created by Feroz on 21/11/2016.
 */

public class ONLYTITLELIST extends Card {
    private RecyclerView recyclerView;
    private TextView textView;
    private OnlyListRecycleAdapter onlyListRecycleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_list, container, false);
        textView = (TextView) view.findViewById(R.id.title);
       // textView.setText("Title for Only Title List");
        Typeface titletf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Regular.ttf");


        recyclerView = (RecyclerView) view.findViewById(R.id.itemListRV);

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");
            if(cms.getTitle() != null){
                textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                textView.setText(cms.getTitle().getText());
                textView.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
                textView.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize())/3);
                textView.setTypeface(titletf,Typeface.BOLD);
            }
            if(cms.getList() != null){

                onlyListRecycleAdapter = new OnlyListRecycleAdapter(cms,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(onlyListRecycleAdapter);

            }



        }

        return view ;
    }


}
