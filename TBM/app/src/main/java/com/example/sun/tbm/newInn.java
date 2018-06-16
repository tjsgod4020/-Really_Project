package com.example.sun.tbm;

public class newInn{
    private String tripName;
    private String tripDay;
    private String innName;
    private String innAdress;
    private String innNumber;
    private int innMoney;
    private String kind;

    public newInn(){

    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setTripDay(String tripDay) {
        this.tripDay = tripDay;
    }

    public void setInnAdress(String innAdress) {
        this.innAdress = innAdress;
    }

    public void setInnMoney(int innMoney) {
        this.innMoney = innMoney;
    }

    public void setInnName(String innName) {
        this.innName = innName;
    }

    public void setInnNumber(String innNumber) {
        this.innNumber = innNumber;
    }

    public String getTripName() {
        return tripName;
    }

    public String getTripDay() {
        return tripDay;
    }

    public int getInnMoney() {
        return innMoney;
    }

    public String getInnAdress() {
        return innAdress;
    }

    public String getInnName() {
        return innName;
    }

    public String getInnNumber() {
        return innNumber;
    }
}

