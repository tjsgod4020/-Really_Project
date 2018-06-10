package com.example.sun.tbm;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.widget.TimePicker;
import android.app.TimePickerDialog;


public class TrafficActivity extends AppCompatActivity {
        int tHour, tMinute;
        TextView  tTime;
        Calendar cal = new GregorianCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_traffic);
            tTime = (TextView)findViewById(R.id.editView_TRtime);
            tHour = cal.get(Calendar.HOUR_OF_DAY);
            tMinute = cal.get(Calendar.MINUTE);
            UpdateNow();

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
            case R.id.button_TRd:
                new TimePickerDialog(TrafficActivity.this, tTimeSetListener, tHour,
                    tMinute, true).show();
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
    tTime.setText(String.format("%d시 : %d분", tHour, tMinute));}
}


