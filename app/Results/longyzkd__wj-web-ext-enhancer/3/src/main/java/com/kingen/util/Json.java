package com.kingen.util;
 import java.util.List;
import com.google.common.collect.Lists;
public class Json {

 private  boolean success;

 private  String msg;

 private  Object obj;

 private  List children;


public List getChildren(){
    return children;
}


public void setSuccess(boolean success){
    this.success = success;
}


public String getMsg(){
    return msg;
}


public void setObj(Object obj){
    this.obj = obj;
}


public Object getObj(){
    return obj;
}


public void setChildren(List children){
    this.children = children;
}


public void setMsg(String msg){
    this.msg = msg;
}


public boolean isSuccess(){
    return success;
}


}