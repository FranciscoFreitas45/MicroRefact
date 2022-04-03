package edu.xr.campusweibo.web.rest.util;
 import java.io.Serializable;
public class ResponseResult implements Serializable{

 private  int returnCode;

 private  String returnInfo;


public void setReturnInfo(String returnInfo){
    this.returnInfo = returnInfo;
}


public String getReturnInfo(){
    return returnInfo;
}


public void setReturnCode(int returnCode){
    this.returnCode = returnCode;
}


public int getReturnCode(){
    return returnCode;
}


}