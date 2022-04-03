package com.fosun.fc.projects.creepers.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersShixinAll implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String badRecord;

 private  String code;

 private  String disRecord;

 private  String goodRecord;

 private  String memo;

 private  String name;

 private  String province;

public TCreepersShixinAll() {
}
public String getName(){
    return this.name;
}


public void setCode(String code){
    this.code = code;
}


public long getId(){
    return this.id;
}


public String getBadRecord(){
    return this.badRecord;
}


public void setDisRecord(String disRecord){
    this.disRecord = disRecord;
}


public String getGoodRecord(){
    return this.goodRecord;
}


public String getMemo(){
    return this.memo;
}


public String getDisRecord(){
    return this.disRecord;
}


public String getProvince(){
    return this.province;
}


public String getCode(){
    return this.code;
}


}