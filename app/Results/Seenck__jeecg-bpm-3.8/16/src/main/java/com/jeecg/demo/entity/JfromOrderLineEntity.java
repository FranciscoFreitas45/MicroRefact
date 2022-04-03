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
@Table(name = "jfrom_order_line", schema = "")
@SuppressWarnings("serial")
public class JfromOrderLineEntity {

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

 private  java.lang.String orderid;

@Excel(name = "商品名称", width = 15)
 private  java.lang.String itemName;

@Excel(name = "商品数量", width = 15)
 private  java.lang.Integer qty;

@Excel(name = "商品价格", width = 15)
 private  java.math.BigDecimal price;

@Excel(name = "金额", width = 15)
 private  java.math.BigDecimal amount;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
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


@Column(name = "QTY", nullable = true, length = 32)
public java.lang.Integer getQty(){
    return this.qty;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setPrice(java.math.BigDecimal price){
    this.price = price;
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


public void setAmount(java.math.BigDecimal amount){
    this.amount = amount;
}


public void setBpmStatus(java.lang.String bpmStatus){
    this.bpmStatus = bpmStatus;
}


@Column(name = "AMOUNT", nullable = true, length = 32)
public java.math.BigDecimal getAmount(){
    return this.amount;
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


@Column(name = "ORDERID", nullable = true, length = 36)
public java.lang.String getOrderid(){
    return this.orderid;
}


public void setOrderid(java.lang.String orderid){
    this.orderid = orderid;
}


@Column(name = "PRICE", nullable = true, length = 32)
public java.math.BigDecimal getPrice(){
    return this.price;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "ITEM_NAME", nullable = true, length = 128)
public java.lang.String getItemName(){
    return this.itemName;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setQty(java.lang.Integer qty){
    this.qty = qty;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


public void setItemName(java.lang.String itemName){
    this.itemName = itemName;
}


}