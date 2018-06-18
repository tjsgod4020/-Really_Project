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
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.sql.Types.NULL;

public class TripListActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getReference("User");

    private ListView itemList_T;
    private Button button_Ts;


    private int counter;
    private List<newTrip> trip = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        itemList_T = (ListView)findViewById(R.id.itemList_T);
        button_Ts = (Button)findViewById(R.id.button_Ts);

        title.add("확인을 눌러주세요.");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 ,title);
        itemList_T.setAdapter(adapter);
        itemList_T.setOnItemClickListener(onItemClickListener);

        button_Ts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(tripListener);
            }
        });
    }

    public void tripCancelButton(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    //List View Item 이벤트
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //리스트 뷰 갱신
            if(title.get(0).equals("확인을 눌러주세요.")){
                Toast.makeText(getApplicationContext(), "확인을 눌러주세요", Toast.LENGTH_SHORT).show();
            }else {
                String tripName = trip.get(position).getTripName();
                tripData tripdata = (tripData) getApplication();
                tripdata.setTripTitle(tripName);
                tripdata.setTripNumber(position);
                Intent intent = new Intent(getApplicationContext(), DateListActivity.class);
                startActivity(intent);
            }
        }
    };

    ValueEventListener tripListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //변수들 초기화
            counter = 0;
            title.clear();
            trip.clear();
            //DB에서 데이터 읽어온 후 trip에 데이터 적재 및 갯수 파악.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                newTrip temp = snapshot.getValue(newTrip.class);
                trip.add(temp);
                counter++;
            }
            //리스트 뷰 아이템 변경
            for(int i = 0; i < counter; i++){
                title.add(trip.get(i).getTripName());
            }
            //확인용 로그
            Log.d("MainActivity", title.get(0));
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
