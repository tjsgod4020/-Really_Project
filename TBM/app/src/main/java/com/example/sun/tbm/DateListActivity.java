package com.example.sun.tbm;

import android.content.Intent;
import android.support.annotation.NonNull;
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
    private List<newTripDate> counting = new ArrayList<>();
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
                tripData tripdata = (tripData) getApplication();
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

    /*
    ValueEventListener dayCounting = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                newTripDate temp = snapshot.getValue(newTripDate.class);
                counting.add(temp);
                counter++;
            }

            mDatabase.addListenerForSingleValueEvent(tripDayListener);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    */

    ValueEventListener tripDayListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //변수들 초기화
            tripData tripdata = (tripData) getApplication();
            String tempDay;
            String[] spliteDay;
            counter = 0;
            day.clear();
            trip.clear();

            //DB에서 데이터 읽어온 후 trip에 데이터
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                newTrip temp = snapshot.getValue(newTrip.class);
                trip.add(temp);
            }
            //날짜 구조변경 필요.
            counter = trip.get(tripdata.getTripNumber()).getTripDayCounter();
            tempDay = trip.get(tripdata.getTripNumber()).getTripTimeS();
            spliteDay = tempDay.split("-");
            int dyearT, dMonthT, dDayT;

            dyearT = Integer.parseInt(spliteDay[0]);
            dMonthT = Integer.parseInt(spliteDay[1]);
            dDayT = Integer.parseInt(spliteDay[2]) - 1;

            for(int i = 0; i < counter; i++){
                if(dMonthT == 1 || dMonthT == 3 || dMonthT == 5 || dMonthT == 7 || dMonthT == 8 || dMonthT == 10 || dMonthT == 12){
                    if(dMonthT == 31){
                        dMonthT = dMonthT + 1;
                        dDayT = 1;

                        if(dMonthT < 10){
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }else{
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }
                    }else{
                        dDayT = dDayT + 1;

                        if(dMonthT < 10){
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }else{
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }

                    }
                }else if(dMonthT == 2){
                    if(dMonthT == 28){
                        dMonthT = dMonthT + 1;
                        dDayT = 1;

                        if(dMonthT < 10){
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }else{
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }
                    }else{
                        dDayT = dDayT + 1;

                        if(dMonthT < 10){
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }else{
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }

                    }
                }else{
                    if(dMonthT == 30){
                        dMonthT = dMonthT + 1;
                        dDayT = 1;

                        if(dMonthT < 10){
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }else{
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }
                    }else{
                        dDayT = dDayT + 1;

                        if(dMonthT < 10){
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }else{
                            if(dDayT < 10){
                                tempDay = (dyearT + "-0" + dMonthT + "-0" + dDayT);
                            }else {
                                tempDay = (dyearT + "-0" + dMonthT + "-" + dDayT);
                            }
                        }
                    }
                }
                day.add(tempDay);
            }
            //리스트뷰 최신화
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
        }
    };
}
