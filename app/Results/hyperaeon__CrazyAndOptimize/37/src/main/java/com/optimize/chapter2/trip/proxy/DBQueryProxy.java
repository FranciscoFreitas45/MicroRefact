package com.optimize.chapter2.trip.proxy;
 public class DBQueryProxy implements IDBQuery{

 private  DBQuery real;


@Override
public String request(){
    if (real == null) {
        real = new DBQuery();
    }
    return real.request();
}


}