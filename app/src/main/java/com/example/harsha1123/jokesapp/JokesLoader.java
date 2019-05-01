package com.example.harsha1123.jokesapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JokesLoader extends AsyncTaskLoader<String> {
    String num;
    String url="http://api.icndb.com/jokes/random/";
    public JokesLoader(Context context, String count1) {
        super(context);
        num=count1;
    }

    @Override
    public String loadInBackground() {

        try {
            URL url1=new URL(num);
            HttpURLConnection connection= (HttpURLConnection) url1.openConnection();
            InputStream is=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            String line="";
            StringBuilder builder=new StringBuilder();
            while ((line=reader.readLine())!=null)
            {
                builder.append(line+"\n");
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
