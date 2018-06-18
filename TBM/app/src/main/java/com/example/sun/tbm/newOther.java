package com.example.sun.tbm;

public class newOther{
    private String tripName;
    private String tripDay;
    private String otherName;
    private int otherMoney;
    private String kind;

    public newOther(){

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

    public void setOtherMoney(int otherMoney) {
        this.otherMoney = otherMoney;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getTripDay() {
        return tripDay;
    }

    public String getTripName() {
        return tripName;
    }

    public int getOtherMoney() {
        return otherMoney;
    }

    public String getOtherName() {
        return otherName;
    }
}
