package com.ipe.module.core.web.util;
 import java.io.Serializable;
public class BodyWrapper implements Serializable{

 private  Long total;

 private  Boolean success;

 private  Object rows;


public void setTotal(Long total){
    this.total = total;
}


public void setSuccess(Boolean success){
    this.success = success;
}


public void setRows(Object rows){
    this.rows = rows;
}


public Long getTotal(){
    return total;
}


public Object getRows(){
    return rows;
}


public Boolean getSuccess(){
    return success;
}


}