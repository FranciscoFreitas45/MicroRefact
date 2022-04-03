package com.circle.utils.commission.compute;
 public class OrderCommissionCompute extends ACommissionCompute{


@Override
public double compute(int incomeUserId,int fromUserId,double sourceMoney){
    return sourceMoney * 0.05;
}


}