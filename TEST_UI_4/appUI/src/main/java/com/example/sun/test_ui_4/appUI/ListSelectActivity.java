package com.example.sun.test_ui_4.appUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_select);
    }

    public void innButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), InnActivity.class);
        startActivity(intent);
    }

    public void trafficButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), TrafficActivity.class);
        startActivity(intent);
    }

    public void leisureButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), LeisureActivity.class);
        startActivity(intent);
    }

    public void otherButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
        startActivity(intent);
    }

    public void cancelListButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), accountListActivity.class);
        startActivity(intent);
    }
}
