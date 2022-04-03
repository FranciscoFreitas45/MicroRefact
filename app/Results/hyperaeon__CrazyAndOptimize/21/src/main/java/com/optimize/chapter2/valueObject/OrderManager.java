package com.optimize.chapter2.valueObject;
 import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class OrderManager extends UnicastRemoteObjectimplements IOrderManager{

 private  long serialVersionUID;

protected OrderManager() throws RemoteException {
    super();
}
@Override
public int getNumber(int id){
    return 20;
}


@Override
public boolean checkUser(int id){
    System.out.println("checkUser ");
    return false;
}


@Override
public Order getOrder(int id){
    Order o = new Order();
    o.setClientName("billy");
    o.setNumber(20);
    o.setProductName("desk");
    return null;
}


@Override
public boolean updateOrder(int id){
    System.out.println("updateOrder ");
    return false;
}


@Override
public String getProdName(int id){
    return "desk";
}


@Override
public String getClientName(int id){
    return "billy";
}


}