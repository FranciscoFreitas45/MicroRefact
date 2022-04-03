package com.circle.pojo.comment;
 public class Comment {

 private  int id;

 private  int orderId;

 private  int goodId;

 private  int userId;

 private  String userName;

 private  int circleId;

 private  int orderDetailId;

 private  String commentText;

 private  String serverCommentText;

 private  String commentTime;

 private  int describeScore;

 private  int serverScore;

 private  int shipScore;


public String getCommentText(){
    return commentText;
}


public int getGoodId(){
    return goodId;
}


public int getOrderId(){
    return orderId;
}


public void setServerCommentText(String serverCommentText){
    this.serverCommentText = serverCommentText;
}


public int getDescribeScore(){
    return describeScore;
}


public int getId(){
    return id;
}


public int getCircleId(){
    return circleId;
}


public void setOrderId(int orderId){
    this.orderId = orderId;
}


public void setCircleId(int circleId){
    this.circleId = circleId;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setCommentTime(String commentTime){
    this.commentTime = commentTime;
}


public void setId(int id){
    this.id = id;
}


public String getUserName(){
    return userName;
}


public void setServerScore(int serverScore){
    this.serverScore = serverScore;
}


public String getCommentTime(){
    return commentTime;
}


public void setGoodId(int goodId){
    this.goodId = goodId;
}


public void setDescribeScore(int describeScore){
    this.describeScore = describeScore;
}


public String getServerCommentText(){
    return serverCommentText;
}


public void setOrderDetailId(int orderDetailId){
    this.orderDetailId = orderDetailId;
}


public int getShipScore(){
    return shipScore;
}


public void setShipScore(int shipScore){
    this.shipScore = shipScore;
}


public int getOrderDetailId(){
    return orderDetailId;
}


public void setCommentText(String commentText){
    this.commentText = commentText;
}


public int getServerScore(){
    return serverScore;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


}