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
@Table(name = "T_CREEPERS_LIST_INSURANCE")
@NamedQuery(name = "TCreepersListInsurance.findAll", query = "SELECT t FROM TCreepersListInsurance t")
public class TCreepersListInsurance {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_LIST_INSURANCE_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_LIST_INSURANCE")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_LIST_INSURANCE_ID_GENERATOR")
 private  long id;

 private  String memo;

@Column(name = "USER_CODE")
 private  String userCode;

 private  String password;

public TCreepersListInsurance() {
}
public void setMemo(String memo){
    this.memo = memo;
}


public void setPassword(String password){
    this.password = password;
}


public String getMemo(){
    return this.memo;
}


public void setUserCode(String userCode){
    this.userCode = userCode;
}


public String getPassword(){
    return password;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return this.id;
}


public String getUserCode(){
    return this.userCode;
}


}