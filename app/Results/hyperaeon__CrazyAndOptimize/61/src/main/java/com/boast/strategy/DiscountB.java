package com.boast.strategy;
 public class DiscountB extends AlgoSuperStrategy{


@Override
public double calculate(double money){
    System.out.println("discountB");
    return money;
}


}