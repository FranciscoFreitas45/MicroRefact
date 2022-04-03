package com.cym.utils;
 import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("返回结果")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult {

@ApiModelProperty("请求结果")
 private  boolean success;

@ApiModelProperty("请求状态 200:请求成功 401:token无效 500:服务器错误")
 private  String status;

@ApiModelProperty("错误信息")
 private  String msg;

@ApiModelProperty("返回内容")
 private  T obj;


public void setSuccess(boolean success){
    this.success = success;
}


public String getMsg(){
    return msg;
}


public void setObj(T obj){
    this.obj = obj;
}


public T getObj(){
    return obj;
}


public String getStatus(){
    return status;
}


public void setMsg(String msg){
    this.msg = msg;
}


public void setStatus(String status){
    this.status = status;
}


public boolean isSuccess(){
    return success;
}


}