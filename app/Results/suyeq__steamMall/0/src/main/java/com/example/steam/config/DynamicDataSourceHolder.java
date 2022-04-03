package com.example.steam.config;
 public class DynamicDataSourceHolder {

 public  String MASTER;

 public  String SLAVE;

 private  ThreadLocal<String> holder;

private DynamicDataSourceHolder() {
// 
}
public String getDataSource(){
    return holder.get();
}


public boolean isMaster(){
    return holder.get().equals(MASTER);
}


public void putDataSource(String key){
    holder.set(key);
}


public void clearDataSource(){
    holder.remove();
}


}