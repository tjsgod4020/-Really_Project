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

        //환율 데이터 불러오기
        try {
            String logExRate = new Task().execute().get();
            Log.i("환율데이터", logExRate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public void newButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), NewCreateActivity.class);

        public void newButtonClick(View v) {
            Intent intent = new Intent(getApplicationContext(), NewCreateActivity.class);
            startActivity(intent);
        }

        public void accountButtonClick(View v) {
            Intent intent = new Intent(getApplicationContext(), AccountListActivity.class);

        startActivity(intent);
    }

}

