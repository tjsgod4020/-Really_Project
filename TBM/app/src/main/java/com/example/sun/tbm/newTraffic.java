package com.example.sun.tbm;

public class newTraffic{
    public String tripName;
    public String trafficName;
    public String trafficTime;
    public int trafficMoney;

    public newTraffic(){

    }

    public newTraffic(String tripName, String trafficName, String trafficTime, int trafficMoney){
        this.tripName = tripName;
        this.trafficName = trafficName;
        this.trafficTime = trafficTime;
        this.trafficMoney = trafficMoney;
    }
}
