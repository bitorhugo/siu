package main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures;

import java.io.Serializable;

public class Point implements Serializable {
    private float x;
    private float y;

    public Point (float x, float y) {
        this.x = x;
        this.y = y;
    }

    

    public float getX() {
        return x;
    }



    public void setX(float x) {
        this.x = x;
    }



    public float getY() {
        return y;
    }



    public void setY(float y) {
        this.y = y;
    }



    public float distX (Point p) {
        return Math.abs(p.x - this.x);
    }

    public float distY (Point p) {
        return Math.abs(p.y - this.y);
    }

    public float dist (Point p) {
        return (float) Math.sqrt(Math.pow(this.distX(p), 2) + Math.pow(this.distY(p), 2));
    }
    
    public void moveX (float delta) {
        this.x += delta;
    }

    public void moveY (double delta) {
        this.y += delta;
    }

    public void move (float x, float y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }
    
    public static void main(String[] args) {
        Point p = new Point (-7, -4);
        System.out.println(p.dist(new Point(17, 6.5f)));
        System.out.println("Hash code of " + p + ": " + p.hashCode());
        System.out.println(p.equals(new Point (-7, -4)));
    }

}
