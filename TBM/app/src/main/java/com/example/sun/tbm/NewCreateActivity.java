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

import java.util.List;

public class NewCreateActivity extends AppCompatActivity {
    private Button button_NCs;
    private EditText editText_NCname, editText_NCtime, editText_NCarea, editText_NCbudget, editText_NCmoney;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();
    private DatabaseReference tripRef = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create);

        button_NCs = (Button) findViewById(R.id.button_NCs);
        editText_NCname = (EditText)findViewById(R.id.editText_NCname);
        editText_NCtime = (EditText)findViewById(R.id.editText_NCtime);
        editText_NCarea = (EditText)findViewById(R.id.editText_NCarea);
        editText_NCbudget = (EditText)findViewById(R.id.editText_NCbudget);
        editText_NCmoney = (EditText)findViewById(R.id.editText_NCmoney);

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
}

