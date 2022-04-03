package com.algorithm.sort;
 public class BinaryInsertSort {


public void binarySort(int[] data){
    for (int i = 1; i < data.length; i++) {
        if (data[i] < data[i - 1]) {
            int tmp = data[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (low + high) >> 1;
                if (data[mid] < tmp) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            for (int j = i; j > low; j--) {
                data[j] = data[j - 1];
            }
            data[low] = tmp;
        }
    }
}


public void main(String[] args){
    int[] data = { 9, 8, 7, 12, 5, 4, 3, 2, 1, 0 };
    SortUtils.printData(data);
    binarySort(data);
    SortUtils.printData(data);
}


}