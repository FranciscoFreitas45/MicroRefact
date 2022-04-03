package com.ukefu.util.webim;
 import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
public class WebIMClient {

 private  String userid;

 private  String client;

 private  SseEmitter sse;

public WebIMClient(String userid, String client, SseEmitter sse) {
    this.userid = userid;
    this.sse = sse;
    this.client = client;
}
public void setSse(SseEmitter sse){
    this.sse = sse;
}


public void setClient(String client){
    this.client = client;
}


public SseEmitter getSse(){
    return sse;
}


public String getClient(){
    return client;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


}