package com.cym.ext;
 public class NetworkInfo {

 private Double send;

 private Double receive;

 private String time;


public void setReceive(Double receive){
    this.receive = receive;
}


public Double getReceive(){
    return receive;
}


public void setSend(Double send){
    this.send = send;
}


public String getTime(){
    return time;
}


public Double getSend(){
    return send;
}


public void setTime(String time){
    this.time = time;
}


}