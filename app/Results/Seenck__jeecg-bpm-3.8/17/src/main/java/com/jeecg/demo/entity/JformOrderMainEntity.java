package com.jeecg.demo.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "jform_order_main", schema = "")
@SuppressWarnings("serial")
public class JformOrderMainEntity {

 private  java.lang.String id;

@Excel(name = "订单号", width = 15)
 private  java.lang.String orderCode;

@Excel(name = "订单日期", width = 15, format = "yyyy-MM-dd")
 private  java.util.Date orderDate;

@Excel(name = "订单金额", width = 15)
 private  java.lang.Double orderMoney;

@Excel(name = "备注", width = 15)
 private  java.lang.String content;

@Excel(name = "订单扫描件", width = 15, dicCode = "sex")
 private  java.lang.String ctype;


@Column(name = "CTYPE", nullable = true, length = 500)
public java.lang.String getCtype(){
    return this.ctype;
}


public void setContent(java.lang.String content){
    this.content = content;
}


@Column(name = "CONTENT", nullable = true, length = 500)
public java.lang.String getContent(){
    return this.content;
}


public void setOrderDate(java.util.Date orderDate){
    this.orderDate = orderDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "ORDER_DATE", nullable = true, length = 20)
public java.util.Date getOrderDate(){
    return this.orderDate;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "ORDER_CODE", nullable = true, length = 50)
public java.lang.String getOrderCode(){
    return this.orderCode;
}


public void setOrderCode(java.lang.String orderCode){
    this.orderCode = orderCode;
}


@Column(name = "ORDER_MONEY", nullable = true, scale = 3, length = 10)
public java.lang.Double getOrderMoney(){
    return this.orderMoney;
}


public void setCtype(java.lang.String ctype){
    this.ctype = ctype;
}


public void setOrderMoney(java.lang.Double orderMoney){
    this.orderMoney = orderMoney;
}


}