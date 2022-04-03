package com.algorithm.sort;
 public class BubbleSort {


public void bubbleSort(int[] source){
    for (int i = source.length - 1; i > 0; i--) {
        for (int j = 0; j < i; j++) {
            if (source[j] > source[j + 1]) {
                SortUtils.swap(source, j, j + 1);
            }
        }
    }
}


public void printArray(int[] source){
    for (int i : source) {
        System.out.print(i + "\t");
    }
}


public void main(String[] args){
    int[] array = { 3, 7, 2, 4, 9, 23, 1, 5, 8, 6 };
    printArray(array);
    bubbleSort(array);
    System.out.println();
    printArray(array);
}


}