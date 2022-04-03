package com.oa.entity;
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
@Table(name = "oa_hall", schema = "")
@SuppressWarnings("serial")
public class OaHallEntity {

 private  java.lang.String id;

 private  java.lang.String bpmStatus;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.lang.String sysOrgCode;

 private  java.util.Date createDate;

 private  java.util.Date updateDate;

@Excel(name = "厅室编号", width = 15)
 private  java.lang.String hallCode;

@Excel(name = "厅室名称", width = 15)
 private  java.lang.String hallName;

@Excel(name = "预约人姓名", width = 15)
 private  java.lang.String orderName;

@Excel(name = "预约开始时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
 private  java.util.Date orderStime;

@Excel(name = "预约结束时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
 private  java.util.Date orderEtime;

@Excel(name = "厅室地址", width = 15)
 private  java.lang.String address;

@Excel(name = "预约状态", width = 15)
 private  java.lang.Integer status;


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


public void setOrderStime(java.util.Date orderStime){
    this.orderStime = orderStime;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setOrderEtime(java.util.Date orderEtime){
    this.orderEtime = orderEtime;
}


@Column(name = "STATUS", nullable = true, length = 1)
public java.lang.Integer getStatus(){
    return this.status;
}


public void setHallName(java.lang.String hallName){
    this.hallName = hallName;
}


@Column(name = "ORDER_NAME", nullable = true, length = 32)
public java.lang.String getOrderName(){
    return this.orderName;
}


public void setOrderName(java.lang.String orderName){
    this.orderName = orderName;
}


@Column(name = "HALL_CODE", nullable = true, length = 32)
public java.lang.String getHallCode(){
    return this.hallCode;
}


@Column(name = "ORDER_ETIME", nullable = true, length = 32)
public java.util.Date getOrderEtime(){
    return this.orderEtime;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "ADDRESS", nullable = true, length = 100)
public java.lang.String getAddress(){
    return this.address;
}


public void setBpmStatus(java.lang.String bpmStatus){
    this.bpmStatus = bpmStatus;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setSysOrgCode(java.lang.String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


public void setAddress(java.lang.String address){
    this.address = address;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


@Column(name = "ORDER_STIME", nullable = true, length = 32)
public java.util.Date getOrderStime(){
    return this.orderStime;
}


@Column(name = "BPM_STATUS", nullable = true, length = 32)
public java.lang.String getBpmStatus(){
    return this.bpmStatus;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "HALL_NAME", nullable = true, length = 50)
public java.lang.String getHallName(){
    return this.hallName;
}


public void setHallCode(java.lang.String hallCode){
    this.hallCode = hallCode;
}


public void setStatus(java.lang.Integer status){
    this.status = status;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


}