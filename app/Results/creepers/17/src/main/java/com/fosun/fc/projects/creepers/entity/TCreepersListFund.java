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
@Table(name = "T_CREEPERS_LIST_FUND")
@NamedQuery(name = "TCreepersListFund.findAll", query = "SELECT t FROM TCreepersListFund t")
public class TCreepersListFund {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_LIST_FUND_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_LIST_FUND")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_LIST_FUND_ID_GENERATOR")
 private  long id;

@Column(name = "USER_CODE")
 private  String userCode;

@Column(name = "PASSWORD")
 private  String password;

 private  String memo;

public TCreepersListFund() {
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