package com.byr.warehouse.Interface;
public interface LogService {

   public void saveOpLog(String username,String operation,String result,String detail);
}