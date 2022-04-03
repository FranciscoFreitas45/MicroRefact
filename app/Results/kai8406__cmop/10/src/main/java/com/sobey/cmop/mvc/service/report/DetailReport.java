package com.sobey.cmop.mvc.service.report;
 import java.math.BigDecimal;
public class DetailReport {

 private  long serialVersionUID;

 private  String type;

 private  String remark;

 private  Integer number;

 private  BigDecimal price;


public Integer getNumber(){
    return number;
}


public String getType(){
    return type;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setType(String type){
    this.type = type;
}


public void setPrice(BigDecimal price){
    this.price = price;
}


public void setNumber(Integer number){
    this.number = number;
}


public BigDecimal getPrice(){
    return price;
}


}