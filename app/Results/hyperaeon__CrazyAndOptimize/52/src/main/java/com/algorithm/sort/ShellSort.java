package com.algorithm.sort;
 public class ShellSort {


public void shellSort2(int[] data){
    int h = 1;
    while (h <= data.length) {
        h = h * 3 + 1;
    }
    while (h > 0) {
        for (int i = h; i < data.length; i += h) {
            if (data[i] < data[i - h]) {
                int tmp = data[i];
                int j = i - h;
                while (j >= 0 && data[j] > tmp) {
                    data[j + h] = data[j];
                    j -= h;
                }
                data[j + h] = tmp;
            }
        }
        h = (h - 1) / 3;
    }
}


public void main(String[] args){
    int[] data = { 5, 3, 6, 2, 1, 9, 4, 8, 7 };
    SortUtils.printData(data);
    shellSort2(data);
    SortUtils.printData(data);
}


public void shellSort(int[] data){
    int h = 1;
    while (h <= data.length / 3) {
        h = 3 * h + 1;
    }
    while (h > 0) {
        for (int i = h; i < data.length; i += h) {
            if (data[i] < data[i - h]) {
                int tmp = data[i];
                int j = i - h;
                while (j >= 0 && data[j] > tmp) {
                    data[j + h] = data[j];
                    j -= h;
                }
                // 由于上述while循环中j=j-h，所以，这里再加h才变成原来的j
                data[j + h] = tmp;
            }
        }
        h = (h - 1) / 3;
    }
}


}