package com.designpattern.template.duplicate;
 public class AbstractCalculator {


public int[] split(String exp,String opt){
    String[] str = exp.split(opt);
    int[] arrInt = new int[str.length];
    arrInt[0] = Integer.parseInt(str[0]);
    arrInt[1] = Integer.parseInt(str[1]);
    return arrInt;
}


public int calculate(int num1,int num2)


}