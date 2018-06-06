package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeisureActivity extends AppCompatActivity {
    private Button button_LSs;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leisure);

        button_LSs = (Button)findViewById(R.id.button_LSs);

        button_LSs.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                writeNewLeisure("test2", "tempName", "leisure",100000);
            }
        });
    }

    private void writeNewLeisure(String leisureID, String tripName, String leisureName, int leisureMoney){
        newLeisure leisure = new newLeisure(tripName, leisureName, leisureMoney);

        mDatabase.child("trip").child("test").child(leisureID).setValue(leisure);
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
