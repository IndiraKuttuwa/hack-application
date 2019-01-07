package com.example.indira.hack;

/**
 * Created by Indira on 3/31/2018.
 */

public class PatwariDetails {
    public Integer Area;
    public String District;
    public Integer PattaNumber;
    public String Crop;
    public String Patwariname;
    public String Typeofland;
    public Integer Yield;
    public PatwariDetails(Integer PattaNumber,Integer Area,String Typeofland,Integer Yield,String Crop)
    {
        this.Area = Area;
        this.PattaNumber = PattaNumber;
        this.Crop = Crop;
        this.Typeofland = Typeofland;
        this.Yield = Yield;
    }



}
