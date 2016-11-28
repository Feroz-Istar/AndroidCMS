package com.example.vaibhav.app.viewpager;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.vaibhav.app.cmspojo.CMSSlide;
import com.example.vaibhav.app.com.example.vaibhav.card.Card;
import com.example.vaibhav.app.com.example.vaibhav.card.CardFactory;

import java.util.List;

/**
 * Created by Vaibhav on 18-11-2016.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<CMSSlide> cmsSlides;
    public ViewPagerAdapter(android.support.v4.app.FragmentManager fm,List<CMSSlide> cmsSlides) {
        super(fm);
        this.cmsSlides = cmsSlides;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        final Bundle bundle = new Bundle();


        bundle.putInt(Card.EXTRA_POSITION, position );


        if(cmsSlides.get(position).getTitle().getText() != null)
        bundle.putString(Card.Title,cmsSlides.get(position).getTitle().getText());



        bundle.putSerializable("CMSSLIDE",cmsSlides.get(position));
        fragment = CardFactory.getCard(cmsSlides.get(position).getTemplateName());


        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return cmsSlides.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    @Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View)o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }


    /*@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub


    }*/
}