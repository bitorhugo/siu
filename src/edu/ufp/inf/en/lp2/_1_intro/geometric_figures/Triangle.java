package edu.ufp.inf.en.lp2._1_intro.geometric_figures;

public class Triangle implements Shape{
    
    private Point lowerRight;
    private Point lowerLeft;
    private Point upper;
    
    public Triangle (Point lowerRight, Point lowerleft, Point upper) {
        this.lowerLeft = lowerleft;
        this.lowerRight = lowerRight;
        this.upper = upper;
    }

    @Override
    public void move(float dx, float dy) {
        this.lowerLeft.move(dx, dy);
        this.lowerRight.move(dx, dy);
        this.upper.move(dx, dy);
    }
    
    @Override
    public float area() {
        float base = this.lowerLeft.dist(this.lowerRight);
        Point middle = new Point ((this.lowerLeft.getX() + this.lowerRight.getX()) / 2, this.lowerRight.getY());
        float height = middle.dist(this.upper);
        float area = (base * height) / 2;
        return area;
    }
    
    @Override
    public float perimeter() {
        float perimeter = this.lowerLeft.dist(this.lowerRight) + this.lowerLeft.dist(this.upper) + lowerRight.dist(this.upper);
        return perimeter;
    }
    
    @Override
    public boolean isInside(Point p) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean isOutside(Point p) {
        // TODO Auto-generated method stub
        return false;
    }

    public static void main(String[] args) {
        Triangle tri = new Triangle(new Point(0, 0), new Point (4.5f, 0), new Point (0, 5));
        System.out.println(tri.area());
    }

}
