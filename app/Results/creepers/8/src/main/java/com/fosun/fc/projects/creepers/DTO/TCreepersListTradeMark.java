package com.fosun.fc.projects.creepers.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersListTradeMark {

 private  long serialVersionUID;

 private  long id;

 private  String memo;

 private  String merName;

public TCreepersListTradeMark() {
}
public String getMemo(){
    return this.memo;
}


public String getMerName(){
    return this.merName;
}


public void setMerName(String merName){
    this.merName = merName;
}


public long getId(){
    return this.id;
}


}