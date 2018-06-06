package com.example.sun.tbm;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TrafficActivity extends AppCompatActivity {
    int tHour, tMinute;
    TextView  tTime;
    Calendar cal = new GregorianCalendar();
    private Button button_TRs;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);

        button_TRs = (Button)findViewById(R.id.button_TRs);
        tTime = (TextView)findViewById(R.id.textView_TRtime);
        tHour = cal.get(Calendar.HOUR_OF_DAY);
        tMinute = cal.get(Calendar.MINUTE);
        UpdateNow();

        button_TRs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                writeNewTraffic("test2","tempName","traffic","test",1000);
            }
        });
    }

    private void writeNewTraffic(String trafficID, String tripName, String trafficName, String trafficTime, int trafficMoney){
        newTraffic traffic = new newTraffic(tripName, trafficName, trafficTime, trafficMoney);

        mDatabase.child("trip").child("test").child(trafficID).setValue(traffic);
    }

    public void trafficOkButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
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

