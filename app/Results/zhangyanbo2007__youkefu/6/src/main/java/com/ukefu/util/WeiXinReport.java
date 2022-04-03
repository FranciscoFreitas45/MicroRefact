package com.ukefu.util;
 public class WeiXinReport {

 private  String data;

 private  long subs;

 private  long unsubs;


public long getUnsubs(){
    return unsubs;
}


public void setData(String data){
    this.data = data;
}


public void setSubs(long subs){
    this.subs = subs;
}


public void setUnsubs(long unsubs){
    this.unsubs = unsubs;
}


public long getSubs(){
    return subs;
}


public String getData(){
    return data;
}


}