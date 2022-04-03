package org.jeecgframework.core.extend.datasource;
 public class DataSourceContextHolder {

 private  ThreadLocal contextHolder;


public void clearDataSourceType(){
    contextHolder.remove();
}


public DataSourceType getDataSourceType(){
    return (DataSourceType) contextHolder.get();
}


public void setDataSourceType(DataSourceType dataSourceType){
    contextHolder.set(dataSourceType);
}


}