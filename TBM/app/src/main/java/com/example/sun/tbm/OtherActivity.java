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

public class OtherActivity extends AppCompatActivity {
    private Button button_Os;
    private EditText editText_Oname, editText_Omoney;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        button_Os = (Button) findViewById(R.id.button_Os);
        editText_Oname = (EditText)findViewById(R.id.editText_Oname);
        editText_Omoney = (EditText)findViewById(R.id.editText_Omoney);

        button_Os.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                tripData tripdata = (tripData) getApplication();
                String tripName, tripDay, Oname;
                int Omoney;

                tripName = tripdata.getTripTitle();
                tripDay = tripdata.getTripDay();
                Oname = editText_Oname.getText().toString();
                Omoney = Integer.parseInt(editText_Omoney.getText().toString());

                writeNewOther(tripName, tripDay, Oname, Omoney);

                Toast.makeText(getApplicationContext(), "기타가 생성되었습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), AccountListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void writeNewOther(String tripName, String tripDay, String otherName, int otherMoney){
        newOther other = new newOther();

        other.setKind("OTHER");
        other.setTripName(tripName);
        other.setTripDay(tripDay);
        other.setOtherName(otherName);
        other.setOtherMoney(otherMoney);

        mDatabase.child("User").child(tripName).child("20180601").child("Other").child(otherName).setValue(other);
    }

    public void othercancelButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListSelectActivity.class);
        startActivity(intent);
    }
}
