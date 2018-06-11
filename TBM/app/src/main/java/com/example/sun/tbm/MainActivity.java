package com.example.sun.tbm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExchangeRatePush ERP = new ExchangeRatePush();

        ERP.makeExRate();
        try {
            ERP.setExRateObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ExchangeRatePull ERPL = new ExchangeRatePull();

        ERPL.getExRate();


    }

    public void newButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), NewCreateActivity.class);
        startActivity(intent);
    }

    public void accountButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), AccountListActivity.class);

        startActivity(intent);
    }
}



