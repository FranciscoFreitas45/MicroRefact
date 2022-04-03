package com.fosun.fc.projects.creepers.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersListInsurance {

 private  long serialVersionUID;

 private  long id;

 private  String memo;

 private  String userCode;

 private  String password;

public TCreepersListInsurance() {
}
public void setPassword(String password){
    this.password = password;
}


public String getMemo(){
    return this.memo;
}


public String getPassword(){
    return password;
}


public long getId(){
    return this.id;
}


public String getUserCode(){
    return this.userCode;
}


}