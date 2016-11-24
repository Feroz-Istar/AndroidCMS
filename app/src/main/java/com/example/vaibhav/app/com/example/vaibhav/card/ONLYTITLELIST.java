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
import com.example.vaibhav.app.com.example.vaibhav.card.adapter.OnlyTitleListNumberedRecycleAdapter;
import com.example.vaibhav.app.com.example.vaibhav.card.adapter.OnlyTitleListRecycleAdapter;

/**
 * Created by Feroz on 21/11/2016.
 */

public class ONLYTITLELIST extends Card {
    private RecyclerView recyclerView;
    private TextView title1,title2;
    private OnlyTitleListRecycleAdapter onlyTitleListRecycleAdapter;
    private OnlyTitleListNumberedRecycleAdapter onlyTitleListNumberedRecycleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_list, container, false);
        title1 = (TextView) view.findViewById(R.id.title);
        title2 = (TextView) view.findViewById(R.id.title1);

        // textView.setText("Title for Only Title List");
        Typeface titletf = Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Thin.ttf");


        recyclerView = (RecyclerView) view.findViewById(R.id.itemListRV);

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");
            if(cms.getTitle() != null){
                title1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                title1.setText(cms.getTitle().getText());
                title1.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
                title1.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize())/3);
                title1.setTypeface(titletf);
            }

            if(cms.getTitle2() != null){
                title2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                title2.setText(cms.getTitle().getText());
                title2.setTextColor(Color.parseColor(cms.getTheme().getTitleFontColor()));
                title2.setTextSize(Integer.parseInt(cms.getTheme().getTitleFontSize())/3);
                title2.setTypeface(titletf);
            }


            if(cms.getList() != null){

                if(cms.getTemplateName().equalsIgnoreCase("ONLY_TITLE_LIST_NUMBERED")){

                onlyTitleListNumberedRecycleAdapter = new OnlyTitleListNumberedRecycleAdapter(cms,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(onlyTitleListNumberedRecycleAdapter);

                }else  if(cms.getTemplateName().equalsIgnoreCase("ONLY_TITLE_LIST")){


                    onlyTitleListRecycleAdapter = new OnlyTitleListRecycleAdapter(cms,getContext());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(onlyTitleListRecycleAdapter);

                }

            }



        }

        return view ;
    }


}
