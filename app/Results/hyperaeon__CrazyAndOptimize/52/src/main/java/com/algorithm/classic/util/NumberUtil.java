package com.algorithm.classic.util;
 public class NumberUtil {

private NumberUtil() {
}
public int[] int2Array(int number){
    int len = 0;
    if (number == 0) {
        len = 1;
    }
    int tmp = number;
    while (tmp != 0) {
        tmp /= 10;
        len++;
    }
    int[] a = new int[len];
    if (number == 0) {
        a[0] = number;
    }
    for (int i = 0; i < len; i++) {
        a[len - 1 - i] = number % 10;
        number /= 10;
    }
    return a;
}


}