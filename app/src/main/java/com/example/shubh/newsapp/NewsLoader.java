package com.example.shubh.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Shubh on 24-11-2018.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<news>> {
    String url;
    NewsLoader(Context context,String url){
        super(context);
        this.url=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<news> loadInBackground() {
        if(url == null){
            return null;
        }
        ArrayList<news> newsArrayList=QueryUtils.fetchnews(url);
        return newsArrayList;
    }
}
