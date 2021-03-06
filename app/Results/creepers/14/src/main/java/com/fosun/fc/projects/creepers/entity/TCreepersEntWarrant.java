package com.fosun.fc.projects.creepers.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_ENT_WARRANT")
@NamedQuery(name = "TCreepersEntWarrant.findAll", query = "SELECT t FROM TCreepersEntWarrant t")
public class TCreepersEntWarrant {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_ENT_WARRANT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ENT_WARRANT")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ENT_WARRANT_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(length = 100)
 private  String creditor;

@Column(name = "DEBT_PERIOD", length = 60)
 private  String debtPeriod;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(length = 100)
 private  String obligor;

@Column(name = "RIGHTS_AMOUNT", length = 60)
 private  String rightsAmount;

@Column(name = "RIGHTS_TYPE", length = 10)
 private  String rightsType;

@Column(name = "WARRANT_PERIOD", length = 60)
 private  String warrantPeriod;

@Column(name = "WARRANT_SCOPE", length = 60)
 private  String warrantScope;

@Column(name = "WARRANT_TYPE", length = 60)
 private  String warrantType;

public TCreepersEntWarrant() {
}
public String getWarrantScope(){
    return this.warrantScope;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public void setRightsAmount(String rightsAmount){
    this.rightsAmount = rightsAmount;
}


public String getWarrantPeriod(){
    return this.warrantPeriod;
}


public void setCreditor(String creditor){
    this.creditor = creditor;
}


public String getMerNo(){
    return this.merNo;
}


public void setObligor(String obligor){
    this.obligor = obligor;
}


public void setRightsType(String rightsType){
    this.rightsType = rightsType;
}


public long getId(){
    return this.id;
}


public String getRightsAmount(){
    return this.rightsAmount;
}


public void setWarrantScope(String warrantScope){
    this.warrantScope = warrantScope;
}


public void setWarrantType(String warrantType){
    this.warrantType = warrantType;
}


public String getRightsType(){
    return this.rightsType;
}


public String getCreditor(){
    return this.creditor;
}


public String getDebtPeriod(){
    return this.debtPeriod;
}


public void setDebtPeriod(String debtPeriod){
    this.debtPeriod = debtPeriod;
}


public String getWarrantType(){
    return this.warrantType;
}


public void setWarrantPeriod(String warrantPeriod){
    this.warrantPeriod = warrantPeriod;
}


public void setId(long id){
    this.id = id;
}


public String getObligor(){
    return this.obligor;
}


}