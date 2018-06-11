package com.example.sun.tbm;

public class newTrip{
    public String key;
    public String tripName;
    public String tripTimeS;
    public String tripTimeE;
    public String tripArea;
    public int tripBudget;
    public String tripMoney;

    public newTrip(){

    }

    public newTrip(String tripName, String tripTimeS, String tripTimeE, String tripArea, int tripBudget, String tripMoney){
        this.tripName = tripName;
        this.tripTimeS = tripTimeS;
        this.tripTimeE = tripTimeE;
        this.tripArea = tripArea;
        this.tripBudget = tripBudget;
        this.tripMoney = tripMoney;
    }
}
