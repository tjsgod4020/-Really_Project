package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DateListActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getReference("User");

    private ListView itemList_D;
    private Button button_Ds;

    private int counter;
    private List<newTrip> trip = new ArrayList<>();
    private List<String> day = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_list);

        itemList_D = (ListView)findViewById(R.id.itemList_D);
        button_Ds = (Button)findViewById(R.id.button_Ds);

        day.add("확인을 눌러주세요.");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 ,day);
        itemList_D.setAdapter(adapter);
        itemList_D.setOnItemClickListener(onItemClickListener);

        button_Ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(tripDayListener);
            }
        });
    }

    public void DateCancelButton(View v) {
        Intent intent = new Intent(getApplicationContext(), TripListActivity.class);
        startActivity(intent);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(day.get(0).equals("확인을 눌러주세요.")){
                Toast.makeText(getApplicationContext(), "확인을 눌러주세요", Toast.LENGTH_SHORT).show();
            }else {
                tripData tripdata = (tripData) getApplication();
                tripdata.setTripDay(day.get(position));
                Intent intent = new Intent(getApplicationContext(), AccountListActivity.class);
                startActivity(intent);
            }
        }
    };

    ValueEventListener tripDayListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //변수들 초기화
            tripData tripdata = (tripData) getApplication();
            counter = 0;
            day.clear();
            trip.clear();
            //DB에서 데이터 읽어온 후 trip에 데이터 적재 및 갯수 파악.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                newTrip temp = snapshot.getValue(newTrip.class);
                trip.add(temp);
                counter++;
            }
            //날짜 구조변경 필요.
            for(int i = 0; i < counter; i++){
                day.add(trip.get(tripdata.getTripNumber()).getTripTimeS() + String.valueOf(i + 1));
            }
            //확인용 로그
            Log.d("MainActivity", day.get(0));
            Log.d("MainActivity2", String.valueOf(counter));
            //리스트뷰 최신화
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
        }
    };
}
