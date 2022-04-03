package com.algorithm.classic;
 import com.algorithm.classic.util.ArrayUtil;
import com.algorithm.sort.SortUtils;
public class MinMaxChange {


public void change(int[] data){
    int minTmp = data[0];
    int maxTmp = data[0];
    int min = 0, max = 0;
    for (int i = 0; i < data.length; i++) {
        if (data[i] > maxTmp) {
            maxTmp = data[i];
            max = i;
        }
        if (data[i] < minTmp) {
            minTmp = data[i];
            min = i;
        }
    }
    SortUtils.swap(data, 0, max);
    SortUtils.swap(data, data.length - 1, min);
}


public void main(String[] args){
    int[] data = { 3, 4, 6, 45, 2, 0, 9 };
    change(data);
    ArrayUtil.printIntArray(data);
}


}