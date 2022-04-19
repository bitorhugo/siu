package edu.ufp.inf.en.lp2._1_intro;

import edu.princeton.cs.algs4.StdRandom;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

public class MyArrayDemo {

    public static void testArrayPrimitives () {
        int []arrInt = new int [10];

        for (int value : arrInt) {
            value = StdRandom.uniform(500);
            System.out.println(value);
        }
        
    }   

    public static void testArrayObjects () {
        Point []arrPoints = new Point [10];
        for (Point point : arrPoints) {
            point = new Point (StdRandom.uniform(0, 5), StdRandom.uniform(0, 5));
            System.out.println(point);
        }
    }

    public static void main(String[] args) {
        //MyArrayDemo.testArrayPrimitives();
        MyArrayDemo.testArrayObjects();
    }
}
