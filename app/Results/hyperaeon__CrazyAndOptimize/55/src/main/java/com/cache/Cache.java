package com.cache;
 public class Cache {

 private  Object data;

 private  long timeOut;

 private  long lastRefreshTime;

public Cache() {
}public Cache(Object data, long timeOut, long lastRefreshTime) {
    this.data = data;
    this.timeOut = timeOut;
    this.lastRefreshTime = lastRefreshTime;
}
public void setTimeOut(long timeOut){
    this.timeOut = timeOut;
}


public void setData(Object data){
    this.data = data;
}


public void setLastRefreshTime(long lastRefreshTime){
    this.lastRefreshTime = lastRefreshTime;
}


public long getTimeOut(){
    return timeOut;
}


public long getLastRefreshTime(){
    return lastRefreshTime;
}


public Object getData(){
    return data;
}


}