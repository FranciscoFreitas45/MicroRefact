package com.designpattern.strategy.duplicate;
 public class AbstractCalculator {


public int[] split(String exp,String opt){
    String[] str = exp.split(opt);
    int[] a = new int[2];
    a[0] = Integer.parseInt(str[0]);
    a[1] = Integer.parseInt(str[1]);
    return a;
}


}