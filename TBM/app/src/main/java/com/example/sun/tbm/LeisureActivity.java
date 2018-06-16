package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeisureActivity extends AppCompatActivity {
    private Button button_LSs;
    private EditText editText_LSname, editText_LSmoney;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leisure);

        button_LSs = (Button)findViewById(R.id.button_LSs);
        editText_LSname = (EditText)findViewById(R.id.editText_LSname);
        editText_LSmoney = (EditText)findViewById(R.id.editText_LSmoney);

        button_LSs.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                tripData tripdata = (tripData) getApplication();
                String tripName, tripDay, LSname;
                int LSmoney;

                tripName = tripdata.getTripTitle();
                tripDay = tripdata.getTripDay();
                LSname = editText_LSname.getText().toString();
                LSmoney =Integer.parseInt(editText_LSmoney.getText().toString());

                writeNewLeisure(tripName, tripDay, LSname, LSmoney);

                Toast.makeText(getApplicationContext(), "레저가 생성되었습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), AccountListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void writeNewLeisure(String tripName, String tripDay, String leisureName, int leisureMoney){
        newLeisure leisure = new newLeisure();

        leisure.setKind("LEISURE");
        leisure.setTripName(tripName);
        leisure.setTripDay(tripDay);
        leisure.setLeisureName(leisureName);
        leisure.setLeisureMoney(leisureMoney);

        mDatabase.child("User").child(tripName).child("20180601").child("Leisure").child(leisureName).setValue(leisure);
    }

    public void innCancelButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
    }
}
