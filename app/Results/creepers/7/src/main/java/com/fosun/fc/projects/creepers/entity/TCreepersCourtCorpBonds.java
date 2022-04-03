package com.fosun.fc.projects.creepers.entity;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_COURT_CORP_BONDS")
@NamedQuery(name = "TCreepersCourtCorpBonds.findAll", query = "SELECT t FROM TCreepersCourtCorpBonds t")
public class TCreepersCourtCorpBonds implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_COURT_CORP_BONDS_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_COURT_CORP_BONDS")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_COURT_CORP_BONDS_ID_GENERATOR")
 private  long id;

 private  String code;

 private  String memo;

 private  String name;

 private  String province;

public TCreepersCourtCorpBonds() {
}
public void setName(String name){
    this.name = name;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public String getName(){
    return this.name;
}


public void setProvince(String province){
    this.province = province;
}


public void setCode(String code){
    this.code = code;
}


public String getProvince(){
    return this.province;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return this.id;
}


public String getCode(){
    return this.code;
}


}