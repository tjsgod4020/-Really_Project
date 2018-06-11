package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class TripListActivity extends AppCompatActivity {
    private ListView itemList_T;
    private ArrayList<HashMap<String, String>> tripList = new ArrayList<HashMap<String, String>>();
    private  HashMap<String, String> listData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        itemList_T = (ListView)findViewById(R.id.itemList_T);
        listData.put("name","여행1");
        listData.put("money", "달러");
        tripList.add(listData);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, tripList,android.R.layout.simple_list_item_2,new String[]{"name", "money"},new int[]{android.R.id.text1,android.R.id.text2});
        itemList_T.setAdapter(simpleAdapter);
    }

    public void tripCancelButton(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
