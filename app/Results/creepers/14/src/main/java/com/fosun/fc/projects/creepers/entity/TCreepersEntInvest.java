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
@Table(name = "T_CREEPERS_ENT_INVEST")
@NamedQuery(name = "TCreepersEntInvest.findAll", query = "SELECT t FROM TCreepersEntInvest t")
public class TCreepersEntInvest {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_ENT_INVEST_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ENT_BASIC")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ENT_INVEST_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(name = "INVESTED_NAME", length = 100)
 private  String investedName;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

public TCreepersEntInvest() {
}
public void setInvestedName(String investedName){
    this.investedName = investedName;
}


public String getInvestedName(){
    return this.investedName;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getMerNo(){
    return this.merNo;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return this.id;
}


}