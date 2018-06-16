package com.example.sun.tbm;

public class tripAccount {
    private String tripName;
    private String tripDay;
    private String trafficName;
    private String trafficTime;
    private int trafficMoney;
    private String kind;

    private String otherName;
    private int otherMoney;

    private String leisureName;
    private int leisureMoney;

    private String innName;
    private String innAdress;
    private String innNumber;
    private int innMoney;

    public tripAccount(){

    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setTripDay(String tripDay) {
        this.tripDay = tripDay;
    }

    public void setTrafficMoney(int trafficMoney) {
        this.trafficMoney = trafficMoney;
    }

    public void setTrafficName(String trafficName) {
        this.trafficName = trafficName;
    }

    public void setTrafficTime(String trafficTime) {
        this.trafficTime = trafficTime;
    }

    public String getTripName() {
        return tripName;
    }

    public String getTripDay() {
        return tripDay;
    }

    public int getTrafficMoney() {
        return trafficMoney;
    }

    public String getTrafficName() {
        return trafficName;
    }

    public String getTrafficTime() {
        return trafficTime;
    }

    public void setOtherMoney(int otherMoney) {
        this.otherMoney = otherMoney;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public int getOtherMoney() {
        return otherMoney;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setLeisureMoney(int leisureMoney) {
        this.leisureMoney = leisureMoney;
    }

    public void setLeisureName(String leisureName) {
        this.leisureName = leisureName;
    }

    public int getLeisureMoney() {
        return leisureMoney;
    }

    public String getLeisureName() {
        return leisureName;
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
