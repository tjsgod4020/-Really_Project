package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InnActivity extends AppCompatActivity {
    private Button button_INNs;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inn);

        button_INNs = (Button) findViewById(R.id.button_INNs);

        button_INNs.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                writeNewInn("test","test", "test","test","010-5245-6548",100000);
            }
        });
    }

    private void writeNewInn(String innID, String tripName, String innName, String innAdress, String innNumber, int innMoney){
        newInn inn = new newInn(tripName, innName, innAdress , innNumber, innMoney);

        mDatabase.child("trip").child("test").child(innID).setValue(inn);
    }

    public void innOkButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
    }

    public void innCancelButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
    }
}

