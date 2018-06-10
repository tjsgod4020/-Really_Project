package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;




import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


public class NewCreateActivity extends AppCompatActivity {
    int mYear, mMonth, mDay;
    int mYear2, mMonth2, mDay2;
    TextView mTxtDate;
    TextView mTxtDate2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create);

        mTxtDate = (TextView)findViewById(R.id.editText_NCtime);//텍스트뷰 연결
        mTxtDate2 = (TextView)findViewById(R.id.editText_NCtime2);

        //calendar 인스턴스 선언
        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mYear2 = cal.get(Calendar.YEAR);
        mMonth2 = cal.get(Calendar.MONTH);
        mDay2 = cal.get(Calendar.DAY_OF_MONTH);

        UpdateNow();//화면에 업데이트
    }
    public void dateSet(View v) {
        switch (v.getId()) {
            //시작 날짜 버튼
            case R.id.button_NCtime:
                new DatePickerDialog(NewCreateActivity.this, mDateSetListener, mYear,
                        mMonth, mDay).show();
                break;
        }
    }
        public void dateSet2(View v) {
        switch (v.getId()) {
            //끝 날짜 버튼
            case R.id.button_NCtime2:
                new DatePickerDialog(NewCreateActivity.this, mDateSetListener2, mYear2,
                        mMonth2, mDay2).show();
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
            mTxtDate.setText(String.format("%d/%d/%d", mYear,
                    mMonth + 1, mDay));
            mTxtDate2.setText(String.format("%d/%d/%d", mYear2,
                    mMonth2 + 1, mDay2));
        }




    public void cancelButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void okButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
