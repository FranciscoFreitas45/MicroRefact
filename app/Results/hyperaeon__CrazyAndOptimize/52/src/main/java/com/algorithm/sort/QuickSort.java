package com.algorithm.sort;
 public class QuickSort {


public void quickSort2(int[] data,int start,int end){
    if (start >= end) {
        return;
    }
    int pivot = data[start];
    int n = 0;
    for (int i = start + 1, j = end; ; ) {
        if (i <= end && data[i] < pivot) {
            i++;
        }
        if (j > start && data[j] > pivot) {
            j--;
        }
        if (i < j) {
            SortUtils.swap(data, i, j);
            n = j;
        } else {
            break;
        }
    }
    SortUtils.swap(data, start, n);
    quickSort(data, start, n - 1);
    quickSort(data, n + 1, end);
}


public void main(String[] args){
    int[] data = { 9, 8, 7, 4, 5, 10, 3, 2, 1, 0 };
    System.out.println("Before sort");
    SortUtils.printData(data);
    System.out.println("After sort");
    // quickSort(data, 0, data.length - 1);
    quickSort2(data, 0, data.length - 1);
    SortUtils.printData(data);
}


public void quickSort(int[] data,int start,int end){
    if (start >= end) {
        return;
    }
    int pivot = data[start];
    int i = start + 1;
    int j = end;
    while (true) {
        while (i <= end && data[i] < pivot) {
            i++;
        }
        while (j > start && data[j] > pivot) {
            j--;
        }
        if (i < j) {
            SortUtils.swap(data, i, j);
        } else {
            break;
        }
    }
    SortUtils.swap(data, start, j);
    SortUtils.printData(data);
    quickSort(data, start, j - 1);
    quickSort(data, j + 1, end);
}


}