package com.designpattern.template;
 public class AbstractCalculator {


public int[] split(String exp,String opt){
    String[] arr = exp.split(opt);
    int[] arrayInt = new int[2];
    arrayInt[0] = Integer.valueOf(arr[0]);
    arrayInt[1] = Integer.valueOf(arr[1]);
    return arrayInt;
}


public int calculate(int num1,int num2)


}