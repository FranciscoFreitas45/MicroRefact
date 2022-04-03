package com.boast.strategy;
 public class DiscountC extends AlgoSuperStrategy{


@Override
public double calculate(double money){
    System.out.println("discountc");
    return money;
}


}