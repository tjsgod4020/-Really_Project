package com.example.sun.tbm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.DatePickerDialog;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Date;

public class NewCreateActivity extends AppCompatActivity {
    private Button button_NCs;
    private EditText editText_NCname, editText_NCtime, editText_NCarea, editText_NCbudget, editText_NCmoney;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();
    private DatabaseReference tripRef = database.getReference("User");

    int mYear, mMonth, mDay;
    int mYear2, mMonth2, mDay2;
    TextView mTxtDate;
    TextView mTxtDate2;
    Spinner spinner;
    String[] item;
    SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd");
    String t = c.format(new Date(System.currentTimeMillis()));
    SimpleDateFormat c2 = new SimpleDateFormat("yyyy-MM-dd");
    String t2 = c2.format(new Date(System.currentTimeMillis()));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create);

        button_NCs = (Button) findViewById(R.id.button_NCs);
        editText_NCname = (EditText)findViewById(R.id.editText_NCname);
        editText_NCarea = (EditText)findViewById(R.id.editText_NCarea);
        editText_NCbudget = (EditText)findViewById(R.id.editText_NCbudget);

        spinner = (Spinner)findViewById(R.id.editText_NCmoney);
        mTxtDate = (TextView)findViewById(R.id.editText_NCtime);//텍스트뷰 연결
        mTxtDate2 = (TextView)findViewById(R.id.editText_NCtime2);

        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        mYear2 = cal.get(Calendar.YEAR);
        mMonth2 = cal.get(Calendar.MONTH);
        mDay2 = cal.get(Calendar.DAY_OF_MONTH);

        UpdateNow(); // 뷰에 업데

        spinner = (Spinner)findViewById(R.id.editText_NCmoney);
        item = new String[]{"선택하세요","원", "달러", "유로"}; //리스트
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String cItem= (String)spinner.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );


        button_NCs.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String tripName, tripTimeS, tripTimeE, tripArea, tripMoney;
                int tripBudget;



                tripName = editText_NCname.getText().toString();
                tripTimeS = mTxtDate.getText().toString();
                tripTimeE = mTxtDate2.getText().toString();
                tripArea = editText_NCarea.getText().toString();
                tripBudget = Integer.parseInt(editText_NCbudget.getText().toString());
                tripMoney = "test";
                writeNewTrip(tripName, tripTimeS, tripTimeE, tripArea, tripBudget, tripMoney);
                Toast.makeText(getApplicationContext(), "생성되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void writeNewTrip(String tripName, String tripTimeS, String tripTimeE, String tripArea, int tripBudget, String tripMoney){
        int day = 3;
        newTrip trip = new newTrip();
        newTripDate tripDay = new newTripDate();

        trip.setTripName(tripName);
        trip.setTripTimeS(tripTimeS);
        trip.setTripTimeE(tripTimeE);
        trip.setTripArea(tripArea);
        trip.setTripBudget(tripBudget);
        trip.setTripMoney(tripMoney);
        //============================================================================
        int dyearS, dMonthS, dDayS;
        int dyearE, dMonthE, dDayE;
        int dyearT, dMonthT, dDayT;
        dyearS = mYear;
        dMonthS = mMonth;
        dDayS = mDay;
        dyearE = mYear2;
        dMonthE = mMonth2;
        dDayE = mDay2;
        dyearT = dyearS;
        dMonthT = dMonthS;
        dDayT = dDayS;

        String startDay , endDay, tempDay;

        if(mMonth < 10){
            startDay = (dyearS + "-0" + dMonthS + "-" + dDayS);
        }else{
            startDay = (dyearS + "-" + dMonthS + "-" + dDayS);
        }

        tempDay = startDay;

        if(mMonth2 < 10){
            endDay = (dyearE + "-0" + dMonthE + "-" + dDayE);
        }else{
            endDay = (dyearE + "-" + dMonthE + "-" + dDayE);
        }

        mDatabase.child("User").child(tripName).setValue(trip);

        tripDay.setTripName(tripName);
        tripDay.setTripDay(startDay);
        mDatabase.child("User").child(tripName).child(startDay).setValue(tripDay);

        while (!(tempDay.equals(endDay))){
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

                tripDay.setTripDay(tempDay);
                mDatabase.child("User").child(tripName).child(tempDay).setValue(tripDay);

            }
            tripDay.setTripDay(endDay);
            tripDay.setTripName(tripName);
            mDatabase.child("User").child(tripName).child(endDay).setValue(tripDay);
        }
    }

    public void cancelButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void dateSet(View v) {
        switch (v.getId()) {
            //시작 날짜 버튼
            case R.id.button_NCtime:
                new DatePickerDialog(NewCreateActivity.this, mDateSetListener, mYear, mMonth, mDay).show();
                break;
            default:
                break;
        }
    }

    public void dateSet2(View v) {
        switch (v.getId()) {
            //끝 날짜 버튼
            case R.id.button_NCtime2:
                new DatePickerDialog(NewCreateActivity.this, mDateSetListener2, mYear2, mMonth2, mDay2).show();
                break;
            default:
                break;
        }
    }

    //날짜 리스너
    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;

                    if(monthOfYear < 10){
                        t = (year + "-0" + monthOfYear + "-" + dayOfMonth);
                    }else{
                        t = (year + "-" + monthOfYear + "-" + dayOfMonth);
                    }

                    //뷰에 값을 업데이트
                    UpdateNow();
                }

            };

    DatePickerDialog.OnDateSetListener mDateSetListener2 =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear2 = year;
                    mMonth2 = monthOfYear;
                    mDay2 = dayOfMonth;

                    if(monthOfYear < 10){
                        t2 = (year + "-0" + monthOfYear + "-" + dayOfMonth);
                    }else{
                        t2 = (year + "-" + monthOfYear + "-" + dayOfMonth);
                    }

                    //뷰에 값을 업데이트
                    UpdateNow();
                }
            };

    //뷰에 값을 업데이트 하는 메소드
    void UpdateNow () {
        mTxtDate.setText(t);
        mTxtDate2.setText(t2);
    }
}

