package com.example.sun.tbm;

public class newTraffic{
    private String tripName;
    private String tripDay;
    private String trafficName;
    private String trafficTime;
    private int trafficMoney;
    private String kind;

    public newTraffic(){

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
}
