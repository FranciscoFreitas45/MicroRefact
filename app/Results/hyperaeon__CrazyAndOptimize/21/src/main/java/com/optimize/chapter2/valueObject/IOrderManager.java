package com.optimize.chapter2.valueObject;
 import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IOrderManager extends Remote{


public int getNumber(int id)
;

public boolean checkUser(int id)
;

public Order getOrder(int id)
;

public boolean updateOrder(int id)
;

public String getProdName(int id)
;

public String getClientName(int id)
;

}