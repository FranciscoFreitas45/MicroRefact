package com.optimize.chapter2.duplicate.valueObject;
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
public boolean checkUser(int i){
    return false;
}


@Override
public Order getOrder(int id){
    Order o = new Order();
    o.setClientName("billy");
    o.setNumber(20);
    o.setProductName("desk");
    return o;
}


@Override
public void updateOrder(Order o){
// TODO Auto-generated method stub
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