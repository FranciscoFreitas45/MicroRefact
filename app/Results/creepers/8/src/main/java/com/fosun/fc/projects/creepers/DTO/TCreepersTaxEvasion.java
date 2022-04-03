package com.fosun.fc.projects.creepers.DTO;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersTaxEvasion implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String code;

 private  String memo;

 private  String name;

 private  String province;

public TCreepersTaxEvasion() {
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


public void setCode(String code){
    this.code = code;
}


public String getProvince(){
    return this.province;
}


public long getId(){
    return this.id;
}


public String getCode(){
    return this.code;
}


}