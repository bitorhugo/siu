package edu.ufp.inf.en.lp2._1_intro;

public class MyMath {
    
    private MyMath () {}

    public static double fact_for (int n) {
        double x = 1;
        for (int i = 1; i <= n; ++i) {
            x *= i;
        } 
        return x;
    }

    public static double factorial (int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    public static double pow_iter (double b, double e) {
        int x = 1;
        for (int i = 1; i <= e; ++i) {
            x *= b;
        }
        return x;
    }

    public static double pow_recur (double b, double e) {
        if (e == 0) return 1;
        if (e == 1) return b;
        return b * pow_recur(b, e - 1);
    }

    public static double exp_iter (double n) {
        return pow_iter(Math.E, n);
    }

    public static double exp_recur (double n) {
        return pow_recur(Math.E, n);
    }

    public static void main(String[] args) {
        System.out.println(MyMath.fact_for(10));
        System.out.println(MyMath.factorial(10));
        
        System.out.println(MyMath.pow_recur(7, 5));
        System.out.println(MyMath.pow_iter(7, 5));
        System.out.println(Math.pow(7, 5));

        System.out.println(Math.exp(10));
        System.out.println(MyMath.exp_recur(10));
        System.out.println(MyMath.exp_iter(10));
    }
}
