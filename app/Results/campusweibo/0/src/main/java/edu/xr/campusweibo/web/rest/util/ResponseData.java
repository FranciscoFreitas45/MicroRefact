package edu.xr.campusweibo.web.rest.util;
 public class ResponseData extends ResponseResult{

 private  T data;


public void setData(T data){
    this.data = data;
}


public T getData(){
    return data;
}


}