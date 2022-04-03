package com.optimize.chapter2.duplicate.valueObject;
 import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IOrderManager extends Remote{


public int getNumber(int id)
;

public boolean checkUser(int i)
;

public Order getOrder(int id)
;

public void updateOrder(Order o)
;

public String getProdName(int id)
;

public String getClientName(int id)
;

}