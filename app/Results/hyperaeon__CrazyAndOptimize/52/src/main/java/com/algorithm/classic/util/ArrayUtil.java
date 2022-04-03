package com.algorithm.classic.util;
 public class ArrayUtil {

private ArrayUtil() {
}
public void print2DIntArray(int[][] numbers){
    for (int i = 0; i < numbers.length; i++) {
        for (int j = 0; j < numbers[0].length; j++) {
            System.out.println(numbers[i][j] + " ");
        }
        System.out.println();
    }
}


public void printIntArray(int[] number){
    for (int n : number) {
        System.out.print(n + " ");
    }
}


public void printStringArray(String[] strings){
    for (String n : strings) {
        System.out.print(n + " ");
    }
}


}