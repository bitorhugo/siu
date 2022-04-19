package edu.ufp.inf.en.lp2._1_intro.geometric_figures;


public class Rectangle implements Shape {
    private Point upLeftCorner;
    private Point downRightCorner;


    public Rectangle (Point ulc, Point drc) {
        this.upLeftCorner = ulc;
        this.downRightCorner = drc;
    }


    public Point getUpLeftCorner() {
        return upLeftCorner;
    }


    public void setUpLeftCorner(Point upLeftCorner) {
        this.upLeftCorner = upLeftCorner;
    }


    public Point getDownRightCorner() {
        return downRightCorner;
    }


    public void setDownRightCorner(Point downRightCorner) {
        this.downRightCorner = downRightCorner;
    }


    @Override
    public void move(float dx, float dy) {
        this.upLeftCorner.move(dx, dy);
        this.downRightCorner.move(dx, dy);
    }

    public float diagonal () {
        return this.downRightCorner.dist(this.upLeftCorner);
    }

    @Override
    public float area() {
        float x = this.upLeftCorner.distX(this.downRightCorner);
        float y = this.upLeftCorner.distY(this.downRightCorner);
        return Math.abs(x * y);
    }


    @Override
    public float perimeter() {
        float x = Math.abs(this.upLeftCorner.distX(this.downRightCorner) * 2); 
        float y = Math.abs(this.upLeftCorner.distY(this.downRightCorner) * 2); 
        return x + y;
    }


    @Override
    public boolean isInside(Point p) {
        if (this.upLeftCorner.getX() <= p.getX() && this.downRightCorner.getX() >= p.getX()) {
            return this.upLeftCorner.getY() >= p.getY() && this.downRightCorner.getY() <= p.getY() 
                    ? true
                    : false;
        }
        return false;
    }


    @Override
    public boolean isOutside(Point p) {
        return this.isInside(p) 
                ? false 
                : true;
    }

    public static void main(String[] args) {
        Rectangle rect = new Rectangle(new Point (0, 2), new Point (4, 0));
        System.out.println("Diagonal: " + rect.diagonal());
        System.out.println("Area: " + rect.area());
        System.out.println("Perimeter: " + rect.perimeter());
        System.out.println("isInside: " + rect.isInside(new Point(1, 2)));
        System.out.println("isOutside: " + rect.isOutside(new Point(1, 2)));
    }
}
