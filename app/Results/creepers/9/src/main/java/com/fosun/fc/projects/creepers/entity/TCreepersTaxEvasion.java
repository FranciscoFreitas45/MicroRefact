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
@Table(name = "T_CREEPERS_TAX_EVASION")
@NamedQuery(name = "TCreepersTaxEvasion.findAll", query = "SELECT t FROM TCreepersTaxEvasion t")
public class TCreepersTaxEvasion implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_TAX_EVASION_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_TAX_EVASION")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_TAX_EVASION_ID_GENERATOR")
 private  long id;

 private  String code;

 private  String memo;

 private  String name;

 private  String province;

public TCreepersTaxEvasion() {
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