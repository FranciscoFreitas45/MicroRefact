package com.xwtec.xwserver.pojo.common;
 public class ResponseObj {

 private  ResponseBody body;

 private  ResponseHeader header;


public ResponseBody getBody(){
    return body;
}


public void setBody(ResponseBody body){
    this.body = body;
}


public void setHeader(ResponseHeader header){
    this.header = header;
}


public ResponseHeader getHeader(){
    return header;
}


}