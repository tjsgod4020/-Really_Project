package com.example.sun.tbm;

public class newInn{
    public String tripName;
    public String tripDate;//날짜형식으로 변경해야됌
    public String innName;
    public String innAdress;
    public String innNumber;
    public int innMoney;

    public newInn(){
    }

    public newInn(String tripName,String tripDate,  String innName, String innAdress, String innNumber, int innMoney){
        this.tripName = tripName;
        this.tripDate = tripDate;
        this.innName = innName;
        this.innAdress = innAdress;
        this.innNumber = innNumber;
        this.innMoney = innMoney;
    }
}

