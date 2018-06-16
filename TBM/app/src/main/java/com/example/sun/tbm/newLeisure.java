package com.example.sun.tbm;

public class newLeisure{
    private String tripDay;
    private String tripName;
    private String leisureName;
    private int leisureMoney;
    private String kind;

    public newLeisure(){

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

    public void setLeisureMoney(int leisureMoney) {
        this.leisureMoney = leisureMoney;
    }

    public void setLeisureName(String leisureName) {
        this.leisureName = leisureName;
    }

    public void setTripDay(String tripDay) {
        this.tripDay = tripDay;
    }

    public String getTripDay() {
        return tripDay;
    }

    public String getTripName() {
        return tripName;
    }

    public int getLeisureMoney() {
        return leisureMoney;
    }

    public String getLeisureName() {
        return leisureName;
    }
}
