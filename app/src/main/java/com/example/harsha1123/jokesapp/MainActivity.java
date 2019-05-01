package com.example.harsha1123.jokesapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.number);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                if (ni.isConnected()) {
                    haveConnectedWifi = true;
                }
            }
            else if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;


    }

    public void submit(View view) {

        String number1=num.getText().toString();
        if (number1.length()>0) {
            if (haveNetworkConnection()==false){
                Toast.makeText(this, "you don't have network", Toast.LENGTH_SHORT).show();
            }
            else {
                Integer number = Integer.parseInt(num.getText().toString());
                if (number >= 1 && number <= 100) {
                    haveNetworkConnection();
                    Intent intent = new Intent(this, JokesActivity.class);
                    intent.putExtra("key", number1);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please enter the numbers from 1 to 100", Toast.LENGTH_SHORT).show();
                }
            }
        }else {
            Toast.makeText(this, "Please enter the numbers from 1 to 100", Toast.LENGTH_SHORT).show();
        }
    }
}

