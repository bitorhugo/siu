package edu.ufp.inf.en.lp2._5_figgeo;

import java.io.Serializable;
import java.util.ArrayList;

import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;


public abstract class FigGeo implements FigGeoDimsI, FigGeoDrawI, FigGeoRelsI, Serializable{

    private Color color;
    private ArrayList<Point> points = new ArrayList<>();

    public FigGeo() {}

    public FigGeo (Color color) {
        this.color = color;
    }


    public void addPoint(Point p) {
        if (!this.points.contains(p)) {
            this.points.add(p);
        }
        else {
            System.out.println("Point already exists");
        }
    }

    public Point getPoint(int index) {
        return this.points.get(index);
    }

    public Color getColor () {
        return this.color;
    }
    
    public void setColor (Color color) {
        this.color = color;
    }

}
