package com.jeecg.demo.entity;
 import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "jform_order_main", schema = "")
@SuppressWarnings("serial")
public class JformOrderMain2Entity {

 private  java.lang.String id;

@Excel(name = "订单号", width = 15)
 private  java.lang.String orderCode;

@Excel(name = "订单日期", width = 15, format = "yyyy-MM-dd")
 private  java.util.Date orderDate;

@Excel(name = "订单金额", width = 15)
 private  java.lang.Double orderMoney;

@Excel(name = "备注", width = 15)
 private  java.lang.String content;


public void setContent(java.lang.String content){
    this.content = content;
}


@Column(name = "CONTENT", nullable = true, length = 255)
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


public void setOrderMoney(java.lang.Double orderMoney){
    this.orderMoney = orderMoney;
}


}