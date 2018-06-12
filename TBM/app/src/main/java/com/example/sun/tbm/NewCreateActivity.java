package com.example.sun.tbm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
            }
        });
    }

    private void writeNewTrip(String tripName, String tripTimeS, String tripTimeE, String tripArea, int tripBudget, String tripMoney){
        newTrip trip = new newTrip(tripName, tripTimeS, tripTimeE , tripArea, tripBudget, tripMoney);
        mDatabase.child("유저").child(tripName).setValue(trip);
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

