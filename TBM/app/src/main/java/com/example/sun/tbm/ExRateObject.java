/**
 * @file ExRateObject.java
 * @brief Data format of pushing data(Exchange Rate) to FireBase
 * @author 이병현
 * @date 2018.06.11
 * @version ERP v0.3
 * @state Format is ok . ( The problem was the data type of "Long" and "Double". )

 */

package com.example.sun.tbm;

public class ExRateObject {


    public String cur_nm;
    public String exRate;


    public ExRateObject() {

    }

    public ExRateObject(String cur_nm, String exRate) {

        this.cur_nm = cur_nm;
        this.exRate = exRate;
    }


}
