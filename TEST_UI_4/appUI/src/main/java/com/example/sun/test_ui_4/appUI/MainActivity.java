package com.example.sun.test_ui_4.appUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), newCreateActivity.class);
        startActivity(intent);
    }

    public void accountButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), accountListActivity.class);
        startActivity(intent);
    }

}

