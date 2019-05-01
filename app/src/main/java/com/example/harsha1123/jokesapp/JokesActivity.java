package com.example.harsha1123.jokesapp;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JokesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    RecyclerView rv;
    String count,jokes;
    Integer count1;
    String url;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        rv=findViewById(R.id.recycler);
        url="http://api.icndb.com/jokes/random/";


        //MyTask task=new MyTask();
        count=getIntent().getStringExtra("key");
        Bundle bundle=new Bundle();
        bundle.putString("key",count);
        getLoaderManager().initLoader(1,bundle,this);



        if (getLoaderManager().getLoader(1)!=null){
            getLoaderManager().initLoader(1,null,  this);
        }

    }

    @Override
    public android.content.Loader<String> onCreateLoader(int i, Bundle bundle)
    {
        pd=new ProgressDialog(JokesActivity.this);
        pd.setMessage("Please wait...");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        return new JokesLoader(this,url+bundle.getString("key"));
    }

    @Override
    public void onLoadFinished(android.content.Loader<String> loader, String s)
    {
        pd.dismiss();
        count1=Integer.parseInt(count);
        ArrayList<String> list=new ArrayList<>();

        try {
            JSONObject jsonObject=new JSONObject(s);
            JSONArray array=jsonObject.getJSONArray("value");
            for (int i=0;i<array.length();i++)
            {
                JSONObject index=array.getJSONObject(i);
               /* jokes=index.getString("joke");*/
                list.add(index.getString("joke"));
            }
            JokesModel model=new JokesModel(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter(this,list,count1));
    }

    @Override
    public void onLoaderReset(android.content.Loader<String> loader) {

    }


    //  return new JokesLoader(this,count);



      /*  Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        count1=Integer.parseInt(count);
        try {
            JSONObject jsonObject=new JSONObject(s);
            JSONArray array=jsonObject.getJSONArray("value");
            for (int i=0;i<array.length();i++)
            {
                JSONObject index=array.getJSONObject(i);
                jokes=index.getString("joke");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter(this,jokes,count1));
*/

}
