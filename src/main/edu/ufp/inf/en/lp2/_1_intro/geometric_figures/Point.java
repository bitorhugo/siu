package main.edu.ufp.inf.en.lp2._1_intro.geometric_figures;

public class Point {
    private float x;
    private float y;

    public Point (float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distX (Point p) {
        return p.x - this.x;
    }

    public float distY (Point p) {
        return p.y - this.y;
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
