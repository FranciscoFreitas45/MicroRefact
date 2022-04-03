package com.dtdhehe.ptulife.vo;
 public class ResultVO {

 private  Integer SUCCESS;

 private  Integer FAILED;

 private  String error_code;

 private  String error_msg;

 private  String status;

 private  Object object;


public String getError_msg(){
    return error_msg;
}


public void setError_msg(String error_msg){
    this.error_msg = error_msg;
}


public Object getObject(){
    return object;
}


public String getError_code(){
    return error_code;
}


public void setError_code(String error_code){
    this.error_code = error_code;
}


public String getStatus(){
    return status;
}


public void setObject(Object object){
    this.object = object;
}


public void setStatus(String status){
    this.status = status;
}


}