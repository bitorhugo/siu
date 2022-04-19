package edu.ufp.inf.en.lp2._2_collections;

import java.util.ArrayList;
import java.util.Random;




public class lotoBagArray {
    public static final int MIN_NUM = 1;
    public static final int MAX_NUM = 90;

    private int [] bagInts = new int [MAX_NUM];
    private int remainingBalls;
    private final Random rand = new Random();

    public lotoBagArray () {
        
        // initiate seed
        this.rand.setSeed(System.currentTimeMillis());

        // initiate bagInts 
        for (int i = 0; i < MAX_NUM; i++) {
            while (!addLotoNumber(rand.nextInt(MAX_NUM) + MIN_NUM));
        }
        
    }

    public int[] getBagInts() {
        return bagInts;
    }

    public void setBagInts(int[] bagInts) {
        this.bagInts = bagInts;
    }

    public int getRemainingBalls() {
        return remainingBalls;
    }

    public void setRemainingBalls(int remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    private boolean addLotoNumber (int n) {
        if (!containsLotoNumber(n)) {
            this.bagInts[this.remainingBalls] = n;
            return true;
        }
        return false;
    }

    public boolean containsLotoNumber (int n) {
        if (n < MAX_NUM || n > 1){
            for (int i : bagInts) {
                if (i == n) return true;
            }
        }
        return false;
    }

    public int removeLotoNumber () {
        int i = rand.nextInt(this.remainingBalls);
        return removeLotoNumberAtIndex (i);
    }

    public int removeLotoNumberAtIndex (int index) {
        int value = this.bagInts[index];
        for (int i = index; i < remainingBalls; i++) {
            bagInts[i] = bagInts[i + 1];
        }
        this.bagInts[remainingBalls - 1] = 0;
        this.remainingBalls --;
        return value;
    }

    public ArrayList<Integer> toIntegerArrayList () {
        ArrayList<Integer> al = new ArrayList<>(remainingBalls);

        for (int value : bagInts) {
            al.add(value);
        }

        return al;
    }

    public static void main(String[] args) {
        lotoBagArray lt = new lotoBagArray();
        System.out.println(lt);
        
    }
}
