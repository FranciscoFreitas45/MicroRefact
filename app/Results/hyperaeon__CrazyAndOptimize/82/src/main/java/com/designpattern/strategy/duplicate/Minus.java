package com.designpattern.strategy.duplicate;
 public class Minus extends AbstractCalculatorimplements ICalculator{


@Override
public int calculate(String exp){
    int[] arrayInt = split(exp, "\\-");
    return arrayInt[0] - arrayInt[1];
}


}