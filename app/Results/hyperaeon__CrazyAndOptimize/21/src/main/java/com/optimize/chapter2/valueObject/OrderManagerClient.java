package com.optimize.chapter2.valueObject;
 import java.rmi.Naming;
public class OrderManagerClient {


public void main(String[] args){
    try {
        IOrderManager userManager = (IOrderManager) Naming.lookup("OrderManager");
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            userManager.getOrder(i);
        }
        System.out.println("getOrder spend: " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            userManager.getClientName(i);
            userManager.getProdName(i);
            userManager.getNumber(i);
        }
        System.out.println("3 method call spend: " + (System.currentTimeMillis() - begin));
    } catch (Exception e) {
        System.out.println("OrderManager client exception: " + e);
    }
}


}