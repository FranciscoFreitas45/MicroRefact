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
@Table(name = "T_CREEPERS_INSURANCE_UNIT")
@NamedQuery(name = "TCreepersInsuranceUnit.findAll", query = "SELECT t FROM TCreepersInsuranceUnit t")
public class TCreepersInsuranceUnit {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_INSURANCE_UNIT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_INSURANCE_UNIT")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_INSURANCE_UNIT_ID_GENERATOR")
 private  long id;

@Column(name = "CERT_NO")
 private  String certNo;

 private  String name;

 private  String period;

 private  String unit;

public TCreepersInsuranceUnit() {
}
public String getCertNo(){
    return this.certNo;
}


public void setName(String name){
    this.name = name;
}


public String getName(){
    return this.name;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return this.id;
}


public void setPeriod(String period){
    this.period = period;
}


public String getUnit(){
    return this.unit;
}


public String getPeriod(){
    return this.period;
}


}