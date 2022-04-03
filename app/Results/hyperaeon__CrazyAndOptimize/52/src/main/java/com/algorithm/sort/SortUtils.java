package com.algorithm.sort;
 public class SortUtils {


public void swap(int[] data,int i,int j){
    if (i == j) {
        return;
    }
    data[i] = data[i] + data[j];
    data[j] = data[i] - data[j];
    data[i] = data[i] - data[j];
}


public void printData(int[] data){
    for (int d : data) {
        System.out.print(d + "\t");
    }
    System.out.println();
}


}