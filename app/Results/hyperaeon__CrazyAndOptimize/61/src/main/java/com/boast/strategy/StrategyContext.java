package com.boast.strategy;
 public class StrategyContext {

 private  AlgoSuperStrategy algoSuperStrategy;

public StrategyContext(String type) {
    switch(type) {
        case "normal":
            algoSuperStrategy = new DiscountA();
            break;
        case "return100":
            algoSuperStrategy = new DiscountB();
            break;
        case "discount8":
            algoSuperStrategy = new DiscountC();
            break;
    }
}
public double getResult(double money){
    return algoSuperStrategy.calculate(money);
}


}