package com.example.shubh.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements LoaderCallbacks<ArrayList<news>>{
  private static final String url="https://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=881fc334e173448eb457f86fc33b129e";
  private static  final int id=1;
  private static NewsAdapter newsAdapter;
  private ProgressBar progressBar;
  private ImageView defaultImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connectivityManager=(ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        boolean isConnected=active!=null&&active.isConnectedOrConnecting();
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        defaultImageView=(ImageView) findViewById(R.id.default_image_view);
        if(isConnected) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(id, null, this);
        }
        else {

            progressBar.setVisibility(View.GONE);
            defaultImageView.setImageResource(R.drawable.nointernet);

        }
        final ListView listView=(ListView)findViewById(R.id.mainListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 news currentnews=newsAdapter.getItem(i);
                 Uri newsuri=Uri.parse(currentnews.getUrl());
                Intent in =new Intent(Intent.ACTION_VIEW,newsuri);

                startActivity(in);
            }
        });
        newsAdapter=new NewsAdapter(this,new ArrayList<news>());
        listView.setAdapter(newsAdapter);
    }

    @Override
    public Loader<ArrayList<news>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this,url);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<news>> loader, ArrayList<news> newsList) {
       newsAdapter.clear();
       if(newsList!=null||!newsList.isEmpty()){
           newsAdapter.addAll(newsList);
           progressBar.setVisibility(View.GONE);
       }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<news>> loader) {
       newsAdapter.clear();
    }
}
