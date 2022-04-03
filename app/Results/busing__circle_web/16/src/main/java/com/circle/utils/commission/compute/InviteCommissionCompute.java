package com.circle.utils.commission.compute;
 public class InviteCommissionCompute extends ACommissionCompute{


@Override
public double compute(int incomeUserId,int fromUserId,double sourceMoney){
    return 0.5;
}


}