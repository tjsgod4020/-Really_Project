package com.example.sun.tbm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        button_NCs.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String tripName, tripTimeS, tripTimeE, tripArea, tripMoney;
                int tripBudget;
                tripName = editText_NCname.getText().toString();
                tripTimeS = editText_NCtime.getText().toString();
                tripTimeE = editText_NCtime.getText().toString();
                tripArea = editText_NCarea.getText().toString();
                tripBudget = Integer.parseInt(editText_NCbudget.getText().toString());
                tripMoney = editText_NCmoney.getText().toString();
                writeNewTrip(tripName, tripTimeS, tripTimeE, tripArea, tripBudget, tripMoney);
                Toast.makeText(getApplicationContext(), "생성되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        mYear2 = cal.get(Calendar.YEAR);
        mMonth2 = cal.get(Calendar.MONTH);
        mDay2 = cal.get(Calendar.DAY_OF_MONTH);

        UpdateNow(); // 뷰에 업데

        spinner = (Spinner)findViewById(R.id.editText_NCmoney);
        item = new String[]{"선택하세요","원", "달러", "유로", "a", "b", "c", "d", "e", "f"}; //리스트
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
        mDatabase.child("User").child(tripName).setValue(trip);
        //준비날
        //mDatabase.child("User").child(tripName).child().setValue(tripDay);

        for(int i = 0; i < day; i++){
            tripDay.setTripDay("2018060" + String.valueOf(i + 1));
            tripDay.setTripName(tripName);
            mDatabase.child("User").child(tripName).child("2018060" + String.valueOf(i + 1)).setValue(tripDay);
        }
        //끝난후
        //mDatabase.child("User").child(tripName).child().setValue(tripDay);
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

