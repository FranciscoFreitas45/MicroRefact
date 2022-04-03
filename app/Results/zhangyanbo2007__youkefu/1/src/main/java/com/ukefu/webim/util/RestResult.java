package com.ukefu.webim.util;
 public class RestResult {

 private  long serialVersionUID;

 private  RestResultType status;

 public  Object data;

public RestResult(RestResultType status, Object data) {
    this.status = status;
    this.data = data;
}public RestResult(RestResultType status) {
    this.status = status;
}
public void setData(Object data){
    this.data = data;
}


public RestResultType getStatus(){
    return status;
}


public void setStatus(RestResultType status){
    this.status = status;
}


public Object getData(){
    return data;
}


}