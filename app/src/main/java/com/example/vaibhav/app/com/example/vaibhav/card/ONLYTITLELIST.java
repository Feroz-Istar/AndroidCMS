package com.example.vaibhav.app.com.example.vaibhav.card;

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

import java.util.ArrayList;
import java.util.List;

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

        List<String> lines = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.itemListRV);

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");
            if(cms.getTitle() != null){
                textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                textView.setText(cms.getTitle().getText());
            }
            if(cms.getList() != null){

                if(cms.getList().getList_type().equalsIgnoreCase("SIMPLE_LIST")){
                    System.out.print("list");

                   for(int i = 0; i<cms.getList().getItems().size(); i++){

                       if(cms.getList().getItems().get(i).getText() != null){

                           lines.add(cms.getList().getItems().get(i).getText());
                       }
                     }

                }
                System.out.print("---------------------lines------------------------------"+lines.size());
                onlyListRecycleAdapter = new OnlyListRecycleAdapter(lines,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(onlyListRecycleAdapter);

            }
        }

        return view ;
    }


}
