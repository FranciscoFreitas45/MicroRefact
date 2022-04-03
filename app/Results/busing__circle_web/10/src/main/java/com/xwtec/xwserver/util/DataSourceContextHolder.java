package com.xwtec.xwserver.util;
 public class DataSourceContextHolder {

 private  ThreadLocal<String> dataSourceHolder;


public String getCurrentDataSource(){
    return dataSourceHolder.get();
}


public void switchTo(String dataSourceName){
    dataSourceHolder.set(dataSourceName);
}


public void clear(){
    dataSourceHolder.remove();
}


}