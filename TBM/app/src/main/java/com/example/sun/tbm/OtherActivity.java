package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtherActivity extends AppCompatActivity {
    private Button button_Os;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        button_Os = (Button) findViewById(R.id.button_Os);

        button_Os.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                writeNewOther("test2","tempName","other",10000);
            }
        });
    }

    private void writeNewOther(String innID, String tripName, String otherName, int otherMoney){
        newOther other = new newOther(tripName, otherName, otherMoney);

        mDatabase.child("trip").child("test").child(innID).setValue(other);
    }

    public void otherOkButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
    }

    public void othercancelButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
    }
}
