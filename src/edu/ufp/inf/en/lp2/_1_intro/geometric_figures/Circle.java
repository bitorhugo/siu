package edu.ufp.inf.en.lp2._1_intro.geometric_figures;

public class Circle implements Shape {

    private Point center;
    private Point periphery;

    public Circle (Point center, Point periphery) {
        this.center = center;
        this.periphery = periphery;
    }

    public float radius () {
        return this.center.dist(this.periphery);
    }

    @Override
    public void move(float dx, float dy) {
        this.center.move(dx, dy);
        this.periphery.move(dx, dy);
    }

    @Override
    public float area() {
        return (float) Math.pow(this.radius(), 2) * (float) Math.PI;
    }

    @Override
    public float perimeter() {
        return (this.radius() * (float) Math.PI) * 2;
    }

    @Override
    public boolean isInside(Point p) {
        if (this.center.dist(p) == this.radius()) return true;
        else if (this.center.dist(p) < this.radius()) return true;
        else return false;
    }

    @Override
    public boolean isOutside(Point p) {
        return this.isInside(p)
                ? false 
                : true;
    }
    
    public static void main(String[] args) {
        Circle circ = new Circle(new Point(0, 0), new Point(0, 4));
        System.out.println("Radius: " + circ.radius());
        System.out.println("Area: " + circ.area());
        System.out.println("Perimeter: " + circ.perimeter());
        System.out.println("isInside: " + circ.isInside(new Point(1, 1)));
        System.out.println("isInside: " + circ.isOutside(new Point(1, 1)));
    }
}
