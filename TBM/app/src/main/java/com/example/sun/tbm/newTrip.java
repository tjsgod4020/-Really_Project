package com.example.sun.tbm;

public class newTrip{
    private String tripName;
    private String tripTimeS;
    private String tripTimeE;
    private String tripArea;
    private int tripBudget;
    private String tripMoney;

    public newTrip(){

    }

    public void setTripBudget(int tripBudget) {
        this.tripBudget = tripBudget;
    }

    public void setTripArea(String tripArea) {
        this.tripArea = tripArea;
    }

    public void setTripTimeE(String tripTimeE) {
        this.tripTimeE = tripTimeE;
    }

    public void setTripTimeS(String tripTimeS) {
        this.tripTimeS = tripTimeS;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setTripMoney(String tripMoney) {
        this.tripMoney = tripMoney;
    }

    public String getTripMoney() {
        return tripMoney;
    }

    public int getTripBudget() {
        return tripBudget;
    }

    public String getTripTimeE() {
        return tripTimeE;
    }

    public String getTripTimeS() {
        return tripTimeS;
    }

    public String getTripName() {
        return tripName;
    }

    public String getTripArea() {
        return tripArea;
    }
}
