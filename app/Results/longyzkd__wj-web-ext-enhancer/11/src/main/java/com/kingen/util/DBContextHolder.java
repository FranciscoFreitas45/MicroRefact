package com.kingen.util;
 public class DBContextHolder {

 public  String dataSourceTo;

 public  String dataSourceMySql;

 public  String dataSourceOracle;

 public  String dataSourceSqlserver;

 private  ThreadLocal<String> contextHolder;


public void setDBType(String dbType){
    contextHolder.set(dbType);
}


public String getDBType(){
    return contextHolder.get();
}


public void clearDBType(){
    contextHolder.remove();
}


}