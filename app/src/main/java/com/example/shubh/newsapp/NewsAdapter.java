package com.example.shubh.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shubh on 24-11-2018.
 */

public class NewsAdapter extends ArrayAdapter<news> {
    public NewsAdapter(Context context, ArrayList<news> newsList) {
        super(context, 0,newsList);
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView=convertView;
      if(listItemView == null){
          listItemView= LayoutInflater.from(getContext())
                  .inflate(R.layout.customlayout,parent,false);
      }
      news currentnews=getItem(position);
        TextView author=(TextView)listItemView.findViewById(R.id.author_text_view);
        author.setText(currentnews.getAuthor());
        TextView title=(TextView)listItemView.findViewById(R.id.title_text_view);
        title.setText(currentnews.getTitle());
        TextView desc=(TextView)listItemView.findViewById(R.id.des_text_view);
        desc.setText(currentnews.getDescription());
        return listItemView;
    }
}
