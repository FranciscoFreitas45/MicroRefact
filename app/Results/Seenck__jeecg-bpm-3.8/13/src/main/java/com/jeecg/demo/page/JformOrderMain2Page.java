package com.jeecg.demo.page;
 import java.util.ArrayList;
import java.util.List;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeecg.demo.entity.JformOrderCustomer2Entity;
import com.jeecg.demo.entity.JformOrderMain2Entity;
import com.jeecg.demo.entity.JformOrderTicket2Entity;
public class JformOrderMain2Page {

 private  java.lang.String id;

@Excel(name = "订单号")
 private  java.lang.String orderCode;

@Excel(name = "订单日期", format = "yyyy-MM-dd")
 private  java.util.Date orderDate;

@Excel(name = "订单金额")
 private  java.lang.Double orderMoney;

@Excel(name = "备注")
 private  java.lang.String content;

@ExcelCollection(name = "订单机票信息")
 private  List<JformOrderTicket2Entity> jformOrderTicket2List;

@ExcelCollection(name = "订单客户信息")
 private  List<JformOrderCustomer2Entity> jformOrderCustomer2List;

@JsonIgnore
 private  List<JformOrderMain2Entity> jformOrderMain2List;


public void setContent(java.lang.String content){
    this.content = content;
}


public void setJformOrderMain2List(List<JformOrderMain2Entity> jformOrderMain2List){
    this.jformOrderMain2List = jformOrderMain2List;
}


public java.lang.String getContent(){
    return this.content;
}


public void setJformOrderCustomer2List(List<JformOrderCustomer2Entity> jformOrderCustomer2List){
    this.jformOrderCustomer2List = jformOrderCustomer2List;
}


public java.util.Date getOrderDate(){
    return this.orderDate;
}


public java.lang.String getId(){
    return this.id;
}


public java.lang.String getOrderCode(){
    return this.orderCode;
}


public void setOrderMoney(java.lang.Double orderMoney){
    this.orderMoney = orderMoney;
}


public void setJformOrderTicket2List(List<JformOrderTicket2Entity> jformOrderTicket2List){
    this.jformOrderTicket2List = jformOrderTicket2List;
}


public List<JformOrderMain2Entity> getJformOrderMain2List(){
    return jformOrderMain2List;
}


public List<JformOrderCustomer2Entity> getJformOrderCustomer2List(){
    return jformOrderCustomer2List;
}


public List<JformOrderTicket2Entity> getJformOrderTicket2List(){
    return jformOrderTicket2List;
}


public void setOrderDate(java.util.Date orderDate){
    this.orderDate = orderDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


public void setOrderCode(java.lang.String orderCode){
    this.orderCode = orderCode;
}


public java.lang.Double getOrderMoney(){
    return this.orderMoney;
}


}