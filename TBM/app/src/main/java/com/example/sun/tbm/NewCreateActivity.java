package com.example.sun.tbm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NewCreateActivity extends AppCompatActivity {
    private Button button_NCs;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create);

        button_NCs = (Button) findViewById(R.id.button_NCs);

        button_NCs.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                writeNewTrip("test", "tempName", "tempTimeS","TempTimee","TempArea", 100000,"TempMoney");
            }
        });
    }

    private void writeNewTrip(String tripID, String tripName, String tripTimeS, String tripTimeE, String tripArea, int tripBudget, String tripMoney){
        newTrip trip = new newTrip(tripName, tripTimeS, tripTimeE , tripArea, tripBudget, tripMoney);

        mDatabase.child("trip").child(tripID).setValue(trip);
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

