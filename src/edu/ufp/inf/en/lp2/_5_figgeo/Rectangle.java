package edu.ufp.inf.en.lp2._5_figgeo;

public class Rectangle extends FigGeo{

    public Rectangle() {
        super();
    }

    @Override
    public double area() {
        
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }



    @Override
    public boolean isInside(FigGeo f) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isInterceptedBy(FigGeo f) {
        // TODO Auto-generated method stub
        return false;
    }
    
    public int compareTo (Rectangle r) {
        return this.area() == r.area() && !this.isInside(r) && this.isInterceptedBy(r)
        ? 1
        : 0;
    }

    @Override
    public String toString() {
        return "Area: " + this.area() + "\nPerimeter: " + this.perimeter();
    }
   

}
