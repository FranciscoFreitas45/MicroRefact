package com.fosun.fc.projects.creepers.entity;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_INSURANCE_SUM")
@NamedQuery(name = "TCreepersInsuranceSum.findAll", query = "SELECT t FROM TCreepersInsuranceSum t")
public class TCreepersInsuranceSum {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_INSURANCE_SUM_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_INSURANCE_SUM")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_INSURANCE_SUM_ID_GENERATOR")
 private  long id;

@Column(name = "CERT_NO")
 private  String certNo;

@Column(name = "END_DT")
 private  String endDt;

@Column(name = "ENDOWMENT_SUM")
 private  BigDecimal endowmentSum;

@Column(name = "ENDOWMENT_SUM_PRIVATE")
 private  BigDecimal endowmentSumPrivate;

 private  String memo;

@Column(name = "MONTHS")
 private  BigDecimal months;

 private  String name;

public TCreepersInsuranceSum() {
}
public void setName(String name){
    this.name = name;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getName(){
    return this.name;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public void setEndDt(String endDt){
    this.endDt = endDt;
}


public void setMonths(BigDecimal months){
    this.months = months;
}


public long getId(){
    return this.id;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public void setEndowmentSumPrivate(BigDecimal endowmentSumPrivate){
    this.endowmentSumPrivate = endowmentSumPrivate;
}


public BigDecimal getMonths(){
    return this.months;
}


public void setEndowmentSum(BigDecimal endowmentSum){
    this.endowmentSum = endowmentSum;
}


public String getCertNo(){
    return this.certNo;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public String getEndDt(){
    return this.endDt;
}


public BigDecimal getEndowmentSumPrivate(){
    return this.endowmentSumPrivate;
}


public void setId(long id){
    this.id = id;
}


public BigDecimal getEndowmentSum(){
    return this.endowmentSum;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


}