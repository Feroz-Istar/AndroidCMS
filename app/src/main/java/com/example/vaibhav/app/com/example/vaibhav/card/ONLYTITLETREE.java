package com.example.vaibhav.app.com.example.vaibhav.card;

import android.media.MediaPlayer;
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
import com.example.vaibhav.app.com.example.vaibhav.card.adapter.OnlyTitleTreeRecycleAdapter;
import com.example.vaibhav.app.mediautility.ImageSaver;
import com.example.vaibhav.app.util.CustomLayout;
import com.squareup.picasso.Picasso;

/**
 * Created by Sumanth on 11/23/2016.
 */
public class ONLYTITLETREE extends Card {

    private RecyclerView ParentrecyclerView;
    private TextView textView;
    private OnlyTitleTreeRecycleAdapter onlytitletreeRecycleAdapter;
    private Picasso mPicasso;
    private CustomLayout main_layout;
    private MediaPlayer mPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.only_title_tree, container, false);
        textView = (TextView) view.findViewById(R.id.text);
        mPicasso = Picasso.with(getContext()); //Single instance
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        main_layout = (CustomLayout) view.findViewById(R.id.main_layout);
        mPlayer = new MediaPlayer();

        ParentrecyclerView = (RecyclerView) view.findViewById(R.id.only_title_tree_parent_itemListRV);

        if (getArguments() != null) {
            CMSSlide cms = (CMSSlide)getArguments().getSerializable("CMSSLIDE");
            if(cms != null){

                ThemeUtils themeUtils = new ThemeUtils();
                themeUtils.massageTitle(cms,textView,getContext(),mPlayer);
                themeUtils.massageBackgroundLayout(cms,mPicasso,main_layout,externalReadable,getContext());
                try {
                    mPlayer.prepare();
                }catch (Exception e){

                }
            if(cms.getList() != null){

                onlytitletreeRecycleAdapter = new OnlyTitleTreeRecycleAdapter(cms,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                ParentrecyclerView.setLayoutManager(mLayoutManager);
                ParentrecyclerView.setItemAnimator(new DefaultItemAnimator());
                ParentrecyclerView.setAdapter(onlytitletreeRecycleAdapter);

            }
            }
        }

        return view ;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mPlayer != null ) {
                mPlayer.start();
            }
        } else {
            if (mPlayer != null && mPlayer.isPlaying()) {
                mPlayer.pause();
                mPlayer.seekTo(0);
            }
        }
    }

    @Override
    public void onStop() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            System.out.println("Only titile list destryeddddddddddd");
            mPlayer.stop();
            mPlayer.reset(); // Might not be necessary, since release() is called right after, but it doesn't seem to hurt/cause issues
            mPlayer.release();
            mPlayer = null;
        }
        super.onStop();
    }
}
