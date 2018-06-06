package com.example.sun.tbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InnActivity extends AppCompatActivity {
    private Button button_INNs;
    private EditText editText_INNname, editText_INNadress, editText_INNnumber, editText_INNmoney;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inn);

        button_INNs = (Button) findViewById(R.id.button_INNs);
        editText_INNname = (EditText)findViewById(R.id.editText_INNname);
        editText_INNadress = (EditText)findViewById(R.id.editText_INNadress);
        editText_INNnumber = (EditText)findViewById(R.id.editText_INNnumber);
        editText_INNmoney = (EditText)findViewById(R.id.editText_INNmoney);

        button_INNs.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String tripName, INNname, INNadress, INNnumber;
                int INNmoney;

                INNname = editText_INNname.getText().toString();
                INNadress = editText_INNadress.getText().toString();
                INNnumber = editText_INNnumber.getText().toString();
                INNmoney = Integer.parseInt(editText_INNmoney.getText().toString());

                writeNewInn("test","test",INNname, INNadress, INNnumber, INNmoney);
            }
        });
    }

    private void writeNewInn(String tripName, String tripDate, String innName, String innAdress, String innNumber, int innMoney){
        newInn inn = new newInn(tripName, tripDate, innName , innAdress, innNumber, innMoney);

        mDatabase.child("유저").child(tripName).child(tripDate).setValue(inn);
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

