package com.example.sun.tbm;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//통계를 위한 클래스 및 데이터 베이스 설계 필요.
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

    public void tripListButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), TripListActivity.class);
        startActivity(intent);
    }
}

