package com.example.vaibhav.app;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhav.app.com.example.vaibhav.card.database.DatabaseHandler;
import com.example.vaibhav.app.mediautility.CMSResourceUtility;
import com.example.vaibhav.app.viewpager.SampleActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Feroz on 24/11/2016.
 */

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<String> mDataset;
    private Context context;
    public  class DataObjectHolder extends RecyclerView.ViewHolder
            {
        TextView ppt_id;
        Button detail;
        Button update;
        CoordinatorLayout snackbar;
        public DataObjectHolder(View itemView) {
            super(itemView);
            ppt_id = (TextView) itemView.findViewById(R.id.ppt_id);
            detail = (Button) itemView.findViewById(R.id.detail);
            update = (Button) itemView.findViewById(R.id.update);
            snackbar = (CoordinatorLayout) itemView.findViewById(R.id.coordinatorLayout);
        }


    }



    public MyRecyclerViewAdapter(Context context,List<String> myDataset) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, int position) {
        holder.ppt_id.setText(mDataset.get(position));
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(holder.ppt_id.getText());
                Intent i = new Intent(context, SampleActivity.class);
                i.putExtra("ppt_id", holder.ppt_id.getText());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(i);
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin(context,Integer.parseInt(holder.ppt_id.getText().toString()),holder.snackbar);


            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }






    public void checkLogin(final Context context, final int ppt_id,final CoordinatorLayout coordinatorLayout) {
      final   DatabaseHandler databaseHandler = new DatabaseHandler(context);
        String BASE_URL="";
        if(ppt_id ==0) {
            BASE_URL = context.getResources().getString(R.string.server_ip) + "/get_ppt?ppt_id=416";
        }else{
            BASE_URL = context.getResources().getString(R.string.server_ip) + "/get_ppt?ppt_id="+ppt_id;
        }
        System.out.println("MyRecyclerViewAdapter --- > BASE_URL " + BASE_URL);
        AsyncHttpClient myClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        myClient.setTimeout(120000);
        myClient.setLoggingEnabled(false);
        myClient.get(BASE_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(context, "Something went wrong with server ...",
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onStart() {
                // called before request is starte
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String xml_object = responseString;
                xml_object = xml_object.replaceAll("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
                databaseHandler.updateContent(ppt_id+"",xml_object);;
                System.out.println("Adapter");
                CMSResourceUtility cmsResourceUtility = new CMSResourceUtility(context,xml_object,ppt_id);
                cmsResourceUtility.fetchResource();
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Updating Please Wait ..", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            @Override
            public void onRetry(int retryNo) {
            }
        });


    }

}