package com.optimize.chapter2.valueObject;
 import java.rmi.Naming;
public class NormalBusiness {


public void main(String[] args){
    try {
        IOrderManager userManager = (IOrderManager) Naming.lookup("OrderManager");
        if (userManager.checkUser(1)) {
            Order o = userManager.getOrder(1);
            o.setNumber(1);
            userManager.updateOrder(1);
        }
    } catch (Exception e) {
        System.out.println("OrderManager exception ");
    }
}


}