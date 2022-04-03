package com.jeecg.demo.page;
 import com.jeecg.demo.entity.JfromOrderEntity;
import com.jeecg.demo.entity.JfromOrderLineEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
public class JfromOrderPage {

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

@Excel(name = "收货人")
 private  java.lang.String receiverName;

@Excel(name = "联系电话")
 private  java.lang.String receiverMobile;

@Excel(name = "收货省")
 private  java.lang.String receiverState;

@Excel(name = "收货市")
 private  java.lang.String receiverCity;

@Excel(name = "收货区")
 private  java.lang.String receiverDistrict;

@Excel(name = "收货地址")
 private  java.lang.String receiverAddress;

@ExcelCollection(name = "订单表体")
 private  List<JfromOrderLineEntity> jfromOrderLineList;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setReceiverDistrict(java.lang.String receiverDistrict){
    this.receiverDistrict = receiverDistrict;
}


public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


public void setJfromOrderLineList(List<JfromOrderLineEntity> jfromOrderLineList){
    this.jfromOrderLineList = jfromOrderLineList;
}


public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


public java.lang.String getId(){
    return this.id;
}


public void setReceiverCity(java.lang.String receiverCity){
    this.receiverCity = receiverCity;
}


public java.util.Date getCreateDate(){
    return this.createDate;
}


public java.lang.String getReceiverMobile(){
    return this.receiverMobile;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


public java.lang.String getReceiverState(){
    return this.receiverState;
}


public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public java.lang.String getReceiverCity(){
    return this.receiverCity;
}


public java.lang.String getReceiverAddress(){
    return this.receiverAddress;
}


public void setReceiverMobile(java.lang.String receiverMobile){
    this.receiverMobile = receiverMobile;
}


public void setBpmStatus(java.lang.String bpmStatus){
    this.bpmStatus = bpmStatus;
}


public List<JfromOrderLineEntity> getJfromOrderLineList(){
    return jfromOrderLineList;
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


public java.lang.String getBpmStatus(){
    return this.bpmStatus;
}


public void setReceiverName(java.lang.String receiverName){
    this.receiverName = receiverName;
}


public void setReceiverAddress(java.lang.String receiverAddress){
    this.receiverAddress = receiverAddress;
}


public java.lang.String getUpdateName(){
    return this.updateName;
}


public void setReceiverState(java.lang.String receiverState){
    this.receiverState = receiverState;
}


public java.lang.String getReceiverDistrict(){
    return this.receiverDistrict;
}


public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


public java.lang.String getReceiverName(){
    return this.receiverName;
}


}