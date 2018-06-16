package com.example.sun.tbm;

import android.app.Application;


import java.util.ArrayList;
import java.util.List;

public class tripData extends Application {
    private String tripTitle;
    private int tripNumber;
    private String tripDay;

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }

    public void setTripDay(String tripDay) {
        this.tripDay = tripDay;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public String getTripDay() {
        return tripDay;
    }

}
