package com.designpattern.strategy;
 public class Plus extends AbstractCalculatorimplements ICalculator{


@Override
public int calculate(String exp){
    int[] arrayInt = split(exp, "\\+");
    return arrayInt[0] + arrayInt[1];
}


}