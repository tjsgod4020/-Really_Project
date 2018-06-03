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

<<<<<<< HEAD
    public void newButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), NewCreateActivity.class);
=======
        public void newButtonClick(View v) {
            Intent intent = new Intent(getApplicationContext(), NewCreateActivity.class);
            startActivity(intent);
        }

        public void accountButtonClick(View v) {
            Intent intent = new Intent(getApplicationContext(), AccountListActivity.class);
>>>>>>> 0277ba3aff74a29e7456442e8efd4b9c47ca13aa
        startActivity(intent);
    }

}

