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
@Table(name = "jform_order_customer", schema = "")
@SuppressWarnings("serial")
public class JformOrderCustomer2Entity {

 private  java.lang.String id;

@Excel(name = "客户名", width = 15)
 private  java.lang.String name;

@Excel(name = "单价", width = 15)
 private  java.lang.Double money;

@Excel(name = "性别", width = 15, dicCode = "sex")
 private  java.lang.String sex;

@Excel(name = "电话", width = 15)
 private  java.lang.String telphone;

 private  java.lang.String fkId;


public void setName(java.lang.String name){
    this.name = name;
}


public void setMoney(java.lang.Double money){
    this.money = money;
}


@Column(name = "NAME", nullable = true, length = 32)
public java.lang.String getName(){
    return this.name;
}


public void setTelphone(java.lang.String telphone){
    this.telphone = telphone;
}


public void setSex(java.lang.String sex){
    this.sex = sex;
}


@Column(name = "TELPHONE", nullable = true, length = 32)
public java.lang.String getTelphone(){
    return this.telphone;
}


@Column(name = "SEX", nullable = true, length = 4)
public java.lang.String getSex(){
    return this.sex;
}


public void setFkId(java.lang.String fkId){
    this.fkId = fkId;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "FK_ID", nullable = false, length = 36)
public java.lang.String getFkId(){
    return this.fkId;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "MONEY", nullable = true, scale = 2, length = 10)
public java.lang.Double getMoney(){
    return this.money;
}


}