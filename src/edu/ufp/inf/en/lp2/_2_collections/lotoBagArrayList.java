package edu.ufp.inf.en.lp2._2_collections;

import java.util.ArrayList;
import java.util.Collections;


public class lotoBagArrayList{

    public static final int MIN_NUM = 1;
    public static final int MAX_NUM = 90;

    public ArrayList<Integer> bagIntegers = new ArrayList<>(MAX_NUM);

    public lotoBagArrayList () {
        for (int i = 0; i < MAX_NUM; ++i) {
            bagIntegers.add(i);
        }
        Collections.shuffle(bagIntegers);
    }

    public int[] toIntArray () {
        int[] arr = new int [bagIntegers.size()];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = bagIntegers.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        lotoBagArrayList lbal = new lotoBagArrayList();
        System.out.println(lbal);
    }

}
