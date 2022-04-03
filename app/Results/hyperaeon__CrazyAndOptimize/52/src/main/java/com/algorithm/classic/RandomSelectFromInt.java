package com.algorithm.classic;
 import java.util.Scanner;
import com.algorithm.classic.util.ArrayUtil;
import com.algorithm.classic.util.NumberUtil;
public class RandomSelectFromInt {


public int select2(long number,int start,int end){
    int a = 0;
    a = (int) Math.floor(number % Math.pow(10, end) / Math.pow(10, start - 1));
    return a;
}


public int[] select(int number,int start,int end){
    int[] origin = NumberUtil.int2Array(number);
    if (start > end || (start < 0 && end < 0) || end > origin.length) {
        System.err.println("start or end illegal!");
        System.exit(1);
    }
    int[] result = new int[end - start];
    for (int i = start, j = 0; i < end; i++, j++) {
        result[j] = origin[i];
    }
    return result;
}


public void main(String[] args){
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
        int a = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();
        ArrayUtil.printIntArray(select(a, start, end));
        System.out.println("second method: " + select2(a, start, end));
    }
}


}