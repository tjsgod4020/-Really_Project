/**
 * @file ExchangeRatePush.java
 * @brief Push data(Exchange Raete) to firebase
 * @author 이병현
 * @date 2018.06.04
 * @version ERP v0.1
 * @state why for loop stop at count 1 why???
 */

package com.example.sun.tbm;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;



public class ExchangeRatePush {

    private static String logExRate;
    private String cur_unit;
    private String cur_nm;
    private double ExRate;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getInstance().getReference();

    //환율 데이터 불러오기

    public void makeExRate() {
        try {
            logExRate = new Task().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public void setExRateObject() throws JSONException {

        JSONArray arr = new JSONArray(logExRate);



        for(int count = 0; count < 22; count++) {

            cur_nm = arr.getJSONObject(count).getString("cur_nm");
            cur_unit = arr.getJSONObject(count).getString("cur_unit");
            ExRate = arr.getJSONObject(count).getDouble("deal_bas_r");
            mDatabase.child("Exchange").child(cur_nm).child("currency").setValue(cur_unit);
            mDatabase.child("Exchange").child(cur_nm).child("ExRate").setValue(ExRate);

            //확인용
            Log.i("정보", cur_nm + cur_unit + ExRate + count + arr.length());

        }



    }
}
