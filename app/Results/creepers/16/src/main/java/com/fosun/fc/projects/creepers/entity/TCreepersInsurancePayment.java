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
@Table(name = "T_CREEPERS_INSURANCE_PAYMENT")
@NamedQuery(name = "TCreepersInsurancePayment.findAll", query = "SELECT t FROM TCreepersInsurancePayment t")
public class TCreepersInsurancePayment {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_INSURANCE_PAYMENT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_INSURANCE_PAYMENT")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_INSURANCE_PAYMENT_ID_GENERATOR")
 private  long id;

@Column(name = "CERT_NO")
 private  String certNo;

 private  BigDecimal endowment;

 private  BigDecimal medical;

 private  String name;

@Column(name = "PAYMENT_BASE")
 private  BigDecimal paymentBase;

@Column(name = "PAYMENT_DT")
 private  String paymentDt;

 private  BigDecimal unemployment;

public TCreepersInsurancePayment() {
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return this.name;
}


public void setEndowment(BigDecimal endowment){
    this.endowment = endowment;
}


public String getPaymentDt(){
    return this.paymentDt;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public long getId(){
    return this.id;
}


public BigDecimal getEndowment(){
    return this.endowment;
}


public void setPaymentDt(String paymentDt){
    this.paymentDt = paymentDt;
}


public String getCertNo(){
    return this.certNo;
}


public BigDecimal getUnemployment(){
    return this.unemployment;
}


public void setPaymentBase(BigDecimal paymentBase){
    this.paymentBase = paymentBase;
}


public void setId(long id){
    this.id = id;
}


public BigDecimal getMedical(){
    return this.medical;
}


public BigDecimal getPaymentBase(){
    return this.paymentBase;
}


public void setMedical(BigDecimal medical){
    this.medical = medical;
}


public void setUnemployment(BigDecimal unemployment){
    this.unemployment = unemployment;
}


}