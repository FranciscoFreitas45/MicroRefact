package com.boast.strategy;
 public class DiscountA extends AlgoSuperStrategy{


@Override
public double calculate(double money){
    System.out.println("discountA");
    return money;
}


}