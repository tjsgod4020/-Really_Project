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

public class AccountListActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getReference("User");

    private ListView itemList_A;
    private Button button_ACs;


    private int counterI;
    private int counterT;
    private int counterL;
    private int counterO;
    private List<newTrip> trip = new ArrayList<>();
    private List<newInn> inn = new ArrayList<>();
    private List<newTraffic> traffic = new ArrayList<>();
    private List<newLeisure> leisuer = new ArrayList<>();
    private List<newOther> other = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        itemList_A = (ListView)findViewById(R.id.itemList_A);
        button_ACs = (Button)findViewById(R.id.button_ACs);

        title.add("확인을 눌러주세요.");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 ,title);
        itemList_A.setAdapter(adapter);
        //itemList_A.setOnItemClickListener(onItemClickListener);


        button_ACs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tripData tripdata = (tripData) getApplication();
                mDatabase.child("User").child(tripdata.getTripTitle()).child(tripdata.getTripDay()).child("Inn").addListenerForSingleValueEvent(tripInnListener);
            }
        });
    }

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

    public void addButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
    }

    ValueEventListener tripInnListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //변수들 초기화
            tripData tripdata = (tripData) getApplication();
            counterI = 0;
            inn.clear();
            //DB에서 데이터 읽어온 후 trip에 데이터 적재 및 갯수 파악.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                newInn temp = snapshot.getValue(newInn.class);
                inn.add(temp);
                counterI++;
            }

            mDatabase.child("User").child(tripdata.getTripTitle()).child(tripdata.getTripDay()).child("Traffic").addListenerForSingleValueEvent(tripTrafficListener);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
        }
    };

    ValueEventListener tripTrafficListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //변수들 초기화
            tripData tripdata = (tripData) getApplication();
            counterT = 0;
            traffic.clear();
            //DB에서 데이터 읽어온 후 trip에 데이터 적재 및 갯수 파악.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                newTraffic temp = snapshot.getValue(newTraffic.class);
                traffic.add(temp);
                counterT++;
            }

            mDatabase.child("User").child(tripdata.getTripTitle()).child(tripdata.getTripDay()).child("Leisure").addListenerForSingleValueEvent(tripLeisureListener);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
        }
    };

    ValueEventListener tripLeisureListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //변수들 초기화
            tripData tripdata = (tripData) getApplication();
            counterL = 0;
            leisuer.clear();
            //DB에서 데이터 읽어온 후 trip에 데이터 적재 및 갯수 파악.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                newLeisure temp = snapshot.getValue(newLeisure.class);
                leisuer.add(temp);
                counterL++;
            }

            mDatabase.child("User").child(tripdata.getTripTitle()).child(tripdata.getTripDay()).child("Other").addListenerForSingleValueEvent(tripOtherListener);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
        }
    };

    ValueEventListener tripOtherListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //변수들 초기화
            tripData tripdata = (tripData) getApplication();
            counterO = 0;
            title.clear();
            other.clear();
            //DB에서 데이터 읽어온 후 trip에 데이터 적재 및 갯수 파악.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                newOther temp = snapshot.getValue(newOther.class);
                other.add(temp);
                counterO++;
            }

            //리스트 뷰 아이템 변경
            for(int i = 0; i < counterI; i++){
                title.add(inn.get(i).getInnName());
            }
            for(int i = 0; i < counterT; i++){
                title.add(traffic.get(i).getTrafficName());
            }
            for(int i = 0; i < counterL; i++){
                title.add(leisuer.get(i).getLeisureName());
            }
            for(int i = 0; i < counterO; i++){
                title.add(other.get(i).getOtherName());
            }
            //확인용 로그
            Log.d("MainActivity", title.get(0));
            Log.d("MainActivity2", String.valueOf(counterO));
            //리스트뷰 최신화
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
        }
    };
}
