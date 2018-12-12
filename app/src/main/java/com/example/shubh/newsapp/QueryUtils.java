package com.example.shubh.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Shivang on 24-11-2018.
 */

public class QueryUtils {
    private static final String sample_url="https://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=881fc334e173448eb457f86fc33b129e";
    private static final String LOG_TAG=QueryUtils.class.getSimpleName();
    private QueryUtils(){

    }
    public  static ArrayList<news> fetchnews(String url){
     URL url1=createurl(url);
     String jsonResponse=null;
     try{
        jsonResponse=makeHttpsRequest(url1);
     }
     catch (Exception e){
         Log.e(LOG_TAG,"Error in making the Http connection ",e);
     }
     ArrayList<news> newsArrayList=extractNewsFromJson(jsonResponse);
     return newsArrayList;
    }
    private static String makeHttpsRequest(URL url) throws IOException{
        String jsonResponse=null;
        if(url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try{
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
           inputStream=urlConnection.getInputStream();
           jsonResponse=readFromInputStream(inputStream);
            }
        }
        catch (Exception e){
            Log.e(LOG_TAG,"Error in connection ",e);
        }
        finally {
            if(urlConnection !=null){
                urlConnection.disconnect();
            }
            if(inputStream !=null){
                inputStream.close();
            }
        }
       return jsonResponse;
    }
    private static  String readFromInputStream(InputStream inputStream) throws IOException
    {
        StringBuilder output=new StringBuilder();
        if(inputStream!= null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader =new BufferedReader(inputStreamReader);
            String line=reader.readLine();
            while(line!=null){
                output.append(line);
                line=reader.readLine();
            }
        }
        return output.toString();

    }

     private static URL createurl(String url){
        URL url1=null;
        try{
            url1=new URL(url);
            return url1;
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG,"mal formed excepiton",e);
        }
        return url1;
     }
     // extracting the features from json and the returnig a
    // Arraylist of news class

     public static ArrayList<news> extractNewsFromJson(String Json){
         ArrayList<news> newsArrayList=new ArrayList<>();
           try{
               JSONObject baseJasonResponse=new JSONObject(Json);
               JSONArray jsonArray=baseJasonResponse.getJSONArray("articles");
               for(int i=0;i<jsonArray.length();i++){
                  JSONObject currentNews=jsonArray.getJSONObject(i);
                  String author=currentNews.getString("author");
                  String title=currentNews.getString("title");
                  String desc=currentNews.getString("description");
                  String url=currentNews.getString("url");
                  // creating the object of news class the  i had created to store the author name title
                   // and description of the news
                  news nes=new news(author,title,desc,url);
                  newsArrayList.add(nes);

               }
           }
           catch (Exception e){
               Log.e(LOG_TAG,"Error in extractin the features from the json",e);
           }
           return newsArrayList;
     }


}
