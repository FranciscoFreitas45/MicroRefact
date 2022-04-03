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
@Table(name = "jfrom_order", schema = "")
@SuppressWarnings("serial")
public class JfromOrderEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

 private  java.lang.String bpmStatus;

@Excel(name = "收货人", width = 15)
 private  java.lang.String receiverName;

@Excel(name = "联系电话", width = 15)
 private  java.lang.String receiverMobile;

@Excel(name = "收货省", width = 15)
 private  java.lang.String receiverState;

@Excel(name = "收货市", width = 15)
 private  java.lang.String receiverCity;

@Excel(name = "收货区", width = 15)
 private  java.lang.String receiverDistrict;

@Excel(name = "收货地址", width = 15)
 private  java.lang.String receiverAddress;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setReceiverDistrict(java.lang.String receiverDistrict){
    this.receiverDistrict = receiverDistrict;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
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


public void setReceiverCity(java.lang.String receiverCity){
    this.receiverCity = receiverCity;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "RECEIVER_MOBILE", nullable = true, length = 32)
public java.lang.String getReceiverMobile(){
    return this.receiverMobile;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "RECEIVER_STATE", nullable = true, length = 32)
public java.lang.String getReceiverState(){
    return this.receiverState;
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


@Column(name = "RECEIVER_CITY", nullable = true, length = 32)
public java.lang.String getReceiverCity(){
    return this.receiverCity;
}


@Column(name = "RECEIVER_ADDRESS", nullable = true, length = 128)
public java.lang.String getReceiverAddress(){
    return this.receiverAddress;
}


public void setReceiverMobile(java.lang.String receiverMobile){
    this.receiverMobile = receiverMobile;
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


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "BPM_STATUS", nullable = true, length = 32)
public java.lang.String getBpmStatus(){
    return this.bpmStatus;
}


public void setReceiverName(java.lang.String receiverName){
    this.receiverName = receiverName;
}


public void setReceiverAddress(java.lang.String receiverAddress){
    this.receiverAddress = receiverAddress;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


public void setReceiverState(java.lang.String receiverState){
    this.receiverState = receiverState;
}


@Column(name = "RECEIVER_DISTRICT", nullable = true, length = 32)
public java.lang.String getReceiverDistrict(){
    return this.receiverDistrict;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


@Column(name = "RECEIVER_NAME", nullable = true, length = 56)
public java.lang.String getReceiverName(){
    return this.receiverName;
}


}