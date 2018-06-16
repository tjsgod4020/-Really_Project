package com.example.sun.tbm;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TrafficActivity extends AppCompatActivity {
    int tHour, tMinute;
    TextView  tTime;
    Calendar cal = new GregorianCalendar();
    private Button button_TRs;
    private EditText editText_TRkind, editText_TRmoney;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);

        button_TRs = (Button)findViewById(R.id.button_TRs);
        editText_TRkind = (EditText)findViewById(R.id.editText_TRkind);
        editText_TRmoney = (EditText)findViewById(R.id.editText_TRmoney) ;
        //=========================================================
        tTime = (TextView)findViewById(R.id.textView_TRtime);
        tHour = cal.get(Calendar.HOUR_OF_DAY);
        tMinute = cal.get(Calendar.MINUTE);
        UpdateNow();

        button_TRs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tripData tripdata = (tripData) getApplication();
                String tripName, tripDay, TRname, TRtime;
                int TRmoney;

                tripName = tripdata.getTripTitle();
                tripDay = tripdata.getTripDay();
                TRname = editText_TRkind.getText().toString();
                TRmoney = Integer.parseInt(editText_TRmoney.getText().toString());

                writeNewTraffic(tripName, tripDay, TRname,"test", TRmoney);

                Toast.makeText(getApplicationContext(), "교통이 생성되었습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), AccountListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void writeNewTraffic(String tripName, String tripDay, String trafficName, String trafficTime, int trafficMoney){
        newTraffic traffic = new newTraffic();

        traffic.setKind("TRAFFIC");
        traffic.setTripName(tripName);
        traffic.setTripDay(tripDay);
        traffic.setTrafficName(trafficName);
        traffic.setTrafficTime(trafficTime);
        traffic.setTrafficMoney(trafficMoney);

        mDatabase.child("User").child(tripName).child("20180601").child("Traffic").child(trafficName).setValue(traffic);
    }

    public void cancelOkButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
    }


    public void timeSet(View v){
        switch(v.getId()){

            case R.id.editText_TRtime:
                new TimePickerDialog(TrafficActivity.this, tTimeSetListener, tHour,
                        tMinute, false).show();
                break;
        }
    }

    TimePickerDialog.OnTimeSetListener tTimeSetListener =
            new TimePickerDialog.OnTimeSetListener(){
                @Override
                public void onTimeSet(TimePicker view, int hour, int minute) {
                    tHour = hour;
                    tMinute = minute;
                    UpdateNow();
                }
            };

    void UpdateNow(){
        tTime.setText(String.format("%d시:%d분 입니다.", tHour, tMinute));
    }
}

