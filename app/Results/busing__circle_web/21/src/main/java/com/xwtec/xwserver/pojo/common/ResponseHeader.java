package com.xwtec.xwserver.pojo.common;
 public class ResponseHeader {

 private  String resTime;

 private  String status;

 private  String req_seq;

 private  String key;


public void setResTime(String resTime){
    this.resTime = resTime;
}


public String getKey(){
    return key;
}


public String getReq_seq(){
    return req_seq;
}


public String getStatus(){
    return status;
}


public String getResTime(){
    return resTime;
}


public void setStatus(String status){
    this.status = status;
}


public void setReq_seq(String req_seq){
    this.req_seq = req_seq;
}


public void setKey(String key){
    this.key = key;
}


}