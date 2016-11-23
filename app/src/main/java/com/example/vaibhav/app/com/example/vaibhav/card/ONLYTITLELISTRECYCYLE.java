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
import com.example.vaibhav.app.com.example.vaibhav.card.adapter.OnlyTitleRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 21/11/2016.
 */

public class ONLYTITLELISTRECYCYLE extends Card {
    private RecyclerView recyclerView;
    private OnlyTitleRecycleAdapter onlyTitleRecycleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_list_recycle, container, false);

        List<String> lines = new ArrayList<>();
        lines.add("First line");
        lines.add("Second line");
        lines.add("Really long third line that will wrap and indent properly.");
        lines.add("FOurth");
        lines.add("Really long third line that will wrap and indent properly.");


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        onlyTitleRecycleAdapter = new OnlyTitleRecycleAdapter(lines);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(onlyTitleRecycleAdapter);

        return view ;
    }


}
