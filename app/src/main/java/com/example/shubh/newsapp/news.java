package com.example.shubh.newsapp;

/**
 * Created by Shubh on 24-11-2018.
 */

public class news
{
    private String author;
    private String title;
    private String description;
    private String url;
    public news(String author,String title,String description,String url){
        this.author=author;
        this.title=title;
        this.description=description;
        this.url=url;
    }
    public String getAuthor(){
        return author;

    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getUrl(){
        return url;
    }
}


