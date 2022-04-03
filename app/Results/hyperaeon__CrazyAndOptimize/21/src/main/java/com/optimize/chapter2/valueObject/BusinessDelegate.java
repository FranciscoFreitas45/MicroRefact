package com.optimize.chapter2.valueObject;
 import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
public class BusinessDelegate {

 private IOrderManager userManager;

public BusinessDelegate() {
    try {
        userManager = (IOrderManager) Naming.lookup("OrderManager");
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (RemoteException e) {
        e.printStackTrace();
    } catch (NotBoundException e) {
        e.printStackTrace();
    }
}
public boolean checkUser(int uid){
    if (!checkUserFromCache(uid)) {
        return userManager.checkUser(uid);
    }
    return true;
}


public boolean checkUserFromCache(int uid){
    return true;
}


public Order getOrder(int oid){
    Order order = getOrderFromCache(oid);
    if (order == null) {
        return userManager.getOrder(oid);
    }
    return order;
}


public boolean updateOrder(Order order){
    if (checkUser(1)) {
        Order o = getOrder(1);
        o.setNumber(1);
        userManager.updateOrder(1);
    }
    return true;
}


public Order getOrderFromCache(int oid){
    return null;
}


}