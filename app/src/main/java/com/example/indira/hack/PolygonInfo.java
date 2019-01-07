package com.example.indira.hack;

import com.google.android.gms.maps.model.Polygon;

import java.util.List;

/**
 * Created by Indira on 3/27/2018.
 */

public class PolygonInfo {
    public PolygonPoints polygonref;
    public String cropname;
    public String yield;
    public String state;
    public String district;
    public String addedBy;
    public String addedOn;
    public String LandType;
    public Integer PattaNumber;

    public PolygonInfo(PolygonPoints polygonref1,Integer PattaNumber,String LandType, String cropname1, String yield1, String State1, String District1, String addedBy1, String addedOn1){
        this.polygonref = polygonref1;
        this.cropname = cropname1;
        this.yield = yield1;
        this.state = State1;
        this.district = District1;
        this.addedBy = addedBy1;
        this.addedOn = addedOn1;
        this.LandType = LandType;
        this.PattaNumber = PattaNumber;
    }
}


