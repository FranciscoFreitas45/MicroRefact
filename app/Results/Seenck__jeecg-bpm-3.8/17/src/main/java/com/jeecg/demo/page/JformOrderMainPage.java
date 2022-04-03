package com.jeecg.demo.page;
 import java.util.ArrayList;
import java.util.List;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.jeecg.demo.entity.JformOrderCustomerEntity;
import com.jeecg.demo.entity.JformOrderTicketEntity;
public class JformOrderMainPage {

 private  java.lang.String id;

@Excel(name = "订单号")
 private  java.lang.String orderCode;

@Excel(name = "订单日期", format = "yyyy-MM-dd")
 private  java.util.Date orderDate;

@Excel(name = "订单金额")
 private  java.lang.Double orderMoney;

@Excel(name = "备注")
 private  java.lang.String content;

@Excel(name = "订单扫描件")
 private  java.lang.String ctype;

@ExcelCollection(name = "订单客户信息")
 private  List<JformOrderCustomerEntity> jformOrderCustomerList;

@ExcelCollection(name = "订单机票信息")
 private  List<JformOrderTicketEntity> jformOrderTicketList;


public java.lang.String getCtype(){
    return this.ctype;
}


public void setContent(java.lang.String content){
    this.content = content;
}


public List<JformOrderTicketEntity> getJformOrderTicketList(){
    return jformOrderTicketList;
}


public java.lang.String getContent(){
    return this.content;
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


public void setCtype(java.lang.String ctype){
    this.ctype = ctype;
}


public void setOrderMoney(java.lang.Double orderMoney){
    this.orderMoney = orderMoney;
}


public void setJformOrderCustomerList(List<JformOrderCustomerEntity> jformOrderCustomerList){
    this.jformOrderCustomerList = jformOrderCustomerList;
}


public void setOrderDate(java.util.Date orderDate){
    this.orderDate = orderDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


public void setJformOrderTicketList(List<JformOrderTicketEntity> jformOrderTicketList){
    this.jformOrderTicketList = jformOrderTicketList;
}


public void setOrderCode(java.lang.String orderCode){
    this.orderCode = orderCode;
}


public List<JformOrderCustomerEntity> getJformOrderCustomerList(){
    return jformOrderCustomerList;
}


public java.lang.Double getOrderMoney(){
    return this.orderMoney;
}


}