package com.optimize.chapter2.valueObject;
 import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class OrderManagerServer {


public void main(String[] args){
    try {
        LocateRegistry.createRegistry(1099);
        IOrderManager userManager = new OrderManager();
        Naming.rebind("OrderManager", userManager);
        System.out.println("OrderManager is ready.");
    } catch (Exception e) {
        System.out.println("OrderManager Server failed: " + e);
    }
}


}