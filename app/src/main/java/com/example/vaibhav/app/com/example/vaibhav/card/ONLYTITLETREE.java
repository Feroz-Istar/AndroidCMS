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
import com.example.vaibhav.app.com.example.vaibhav.card.adapter.OnlyTitleTreeRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLYTITLETREE extends Card {

    private RecyclerView ParentrecyclerView,ChildrecyclerView;
    private TextView textView;
    private OnlyTitleTreeRecycleAdapter onlyListRecycleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_tree, container, false);
        textView = (TextView) view.findViewById(R.id.title);
        // textView.setText("Title for Only Title List");

        List<String> Plines = new ArrayList<>();
        List<String> Clines = new ArrayList<>();


        ParentrecyclerView = (RecyclerView) view.findViewById(R.id.only_title_tree_parent_itemListRV);

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");
            if(cms.getList() != null){


                onlyListRecycleAdapter = new OnlyTitleTreeRecycleAdapter(cms,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                ParentrecyclerView.setLayoutManager(mLayoutManager);
                ParentrecyclerView.setItemAnimator(new DefaultItemAnimator());
                ParentrecyclerView.setAdapter(onlyListRecycleAdapter);

            }

        }

        return view ;

    }
    }
