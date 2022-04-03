package com.algorithm.sort;
 public class SelectorSort {


public void selector(int[] data){
    for (int i = 0; i < data.length - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < data.length; j++) {
            if (data[j] < data[minIndex]) {
                minIndex = j;
            }
        }
        SortUtils.swap(data, i, minIndex);
        SortUtils.printData(data);
    }
}


public void main(String[] args){
    int[] data = { 3, 5, 10, 12, 9, 8 };
    SortUtils.printData(data);
    selector(data);
    SortUtils.printData(data);
}


}