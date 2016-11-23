package com.example.vaibhav.app.com.example.vaibhav.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibhav.app.R;
import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.com.example.vaibhav.card.adapter.OnlyListRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLYLIST extends Card {
    private RecyclerView recyclerView;
    private OnlyListRecycleAdapter onlyListRecycleAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_list, container, false);

        List<String> lines = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.only_list_itemListRV);

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");

            if(cms.getList() != null){

                onlyListRecycleAdapter = new OnlyListRecycleAdapter(cms,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(onlyListRecycleAdapter);

                }



        }


        return  view;

    }


}

