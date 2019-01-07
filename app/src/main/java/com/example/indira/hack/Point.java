package com.example.indira.hack;

import java.io.Serializable;

import javax.crypto.SealedObject;

/**
 * Created by Indira on 3/27/2018.
 */

public class Point implements Serializable{
    public double latitude;
    public double longitude;

    Point(double lat, double lng){
        this.latitude = lat;
        this.longitude = lng;
    }
}
