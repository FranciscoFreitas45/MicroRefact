package com.example.steam.localstore.LocalStoreKey;
 import java.util.HashMap;
import java.util.Map;
public class ExpiredTime {

 private long startTime;

 private long expiredTime;

ExpiredTime(long startTime, long expiredTime) {
    this.startTime = startTime;
    this.expiredTime = expiredTime;
}
public long getStartTime(){
    return startTime;
}


public long getExpiredTime(){
    return expiredTime;
}


}