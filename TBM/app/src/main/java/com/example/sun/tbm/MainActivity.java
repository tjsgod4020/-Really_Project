package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

