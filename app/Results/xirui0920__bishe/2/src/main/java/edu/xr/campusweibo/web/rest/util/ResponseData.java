package edu.xr.campusweibo.web.rest.util;
 public class ResponseData extends ResponseResult{

 private  T data;

public ResponseData(int returnCode, String returnInfo, T data) {
    super(returnCode, returnInfo);
    this.data = data;
}
public void setData(T data){
    this.data = data;
}


public T getData(){
    return data;
}


}