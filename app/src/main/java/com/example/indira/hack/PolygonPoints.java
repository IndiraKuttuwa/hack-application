package com.example.indira.hack;

import java.io.Serializable;

/**
 * Created by Indira on 3/27/2018.
 */

public class PolygonPoints implements Serializable {
    public Point point1;
    public Point point2;
    public Point point3;
    public Point point4;

    PolygonPoints(Point pt1, Point pt2, Point pt3, Point pt4){
        this.point1 = pt1;
        this.point2 = pt2;
        this.point3 = pt3;
        this.point4 = pt4;
    }
}
