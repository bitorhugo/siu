package edu.ufp.inf.en.lp2._1_intro.geometric_figures;

public interface Shape {
    
    // Move an object in X by 'dx' value and in Y axis by 'dy' value
    public void move (float dx, float dy);

    // Calculate and return shape area
    public float area ();

    // Calculate and return shape perimeter
    public float perimeter ();

    // Check wether p Point is inside of shape
    public boolean isInside (Point p);
    
    // Check wether p Point is outside of shape
    public boolean isOutside (Point p);
}
