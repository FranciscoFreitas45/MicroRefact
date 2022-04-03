package com.fosun.fc.projects.creepers.entity;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_FUND_EXTRA_DETAIL")
@NamedQuery(name = "TCreepersFundExtraDetail.findAll", query = "SELECT t FROM TCreepersFundExtraDetail t")
public class TCreepersFundExtraDetail {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_FUND_EXTRA_DETAIL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_FUND_EXTRA_DETAIL")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_FUND_EXTRA_DETAIL_ID_GENERATOR")
 private  long id;

@Column(name = "LOGIN_NAME")
 private  String loginName;

 private  BigDecimal amount;

@Column(name = "OPERATION_DESC")
 private  String operationDesc;

@Column(name = "OPERATION_DT")
 private  String operationDt;

@Column(name = "OPERATION_REASON")
 private  String operationReason;

 private  String unit;

public TCreepersFundExtraDetail() {
}
public String getOperationReason(){
    return this.operationReason;
}


public void setOperationReason(String operationReason){
    this.operationReason = operationReason;
}


public void setOperationDt(String operationDt){
    this.operationDt = operationDt;
}


public long getId(){
    return this.id;
}


public String getLoginName(){
    return loginName;
}


public String getOperationDt(){
    return this.operationDt;
}


public String getOperationDesc(){
    return this.operationDesc;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setId(long id){
    this.id = id;
}


public void setOperationDesc(String operationDesc){
    this.operationDesc = operationDesc;
}


public String getUnit(){
    return this.unit;
}


public void setAmount(BigDecimal amount){
    this.amount = amount;
}


public BigDecimal getAmount(){
    return amount;
}


}