package com.circle.pojo.commission;
 public class UserCommission {

 private  int id;

 private  int userId;

 private  double commission;

 private  String lastUpdateTime;

 private  int lastHistoryId;


public double getCommission(){
    return commission;
}


public int getLastHistoryId(){
    return lastHistoryId;
}


public String getLastUpdateTime(){
    return lastUpdateTime;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


public void setCommission(double commission){
    this.commission = commission;
}


public void setLastHistoryId(int lastHistoryId){
    this.lastHistoryId = lastHistoryId;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


public void setLastUpdateTime(String lastUpdateTime){
    this.lastUpdateTime = lastUpdateTime;
}


}