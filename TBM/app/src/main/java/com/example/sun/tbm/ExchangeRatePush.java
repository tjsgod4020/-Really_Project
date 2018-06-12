/**
 * @file ExchangeRatePush.java
 * @brief Push data(Exchange Rate) to FireBase
 * @author 이병현
 * @date 2018.06.11
 * @version ERP v0.3
 * @state push data to FireBase successfully ( The problem was the data type of "Long" and "Double" can`t push over 2 times. )

 */

package com.example.sun.tbm;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;



public class ExchangeRatePush {

    public static String logExRate;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getReference();

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

        for(int indexNm = 0; indexNm < arr.length(); indexNm++) {

            String cur_unit;
            String cur_nm;
            String exRate;

            cur_nm = arr.getJSONObject(indexNm).getString("cur_nm");
            cur_unit = arr.getJSONObject(indexNm).getString("cur_unit");
            exRate = arr.getJSONObject(indexNm).getString("deal_bas_r");

           ExRateObject ERO = new ExRateObject(cur_nm,exRate);

           mDatabase.child("ExRate").child(cur_unit).setValue(ERO);

            //확인용
          //  Log.d("LD1", cur_nm + cur_unit + exRate + 0 + arr.length());

          //  Log.d("LD2", arr.getString(indexNm) + "----"+indexNm+"----");
        }

    }
}
