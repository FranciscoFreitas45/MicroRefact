package com.circle.pojo.circle;
 public class CircleMember {

 private  Integer circleId;

 private  Integer userId;

 private  Integer identityType;

 private  String joinTime;


public String getJoinTime(){
    return joinTime;
}


public void setIdentityType(Integer identityType){
    this.identityType = identityType;
}


public Integer getCircleId(){
    return circleId;
}


public void setJoinTime(String joinTime){
    this.joinTime = joinTime;
}


public void setCircleId(Integer circleId){
    this.circleId = circleId;
}


public Integer getUserId(){
    return userId;
}


public Integer getIdentityType(){
    return identityType;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


}