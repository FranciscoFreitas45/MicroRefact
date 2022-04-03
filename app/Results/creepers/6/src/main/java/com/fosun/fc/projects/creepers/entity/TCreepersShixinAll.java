package com.fosun.fc.projects.creepers.entity;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_SHIXIN_ALL")
@NamedQuery(name = "TCreepersShixinAll.findAll", query = "SELECT t FROM TCreepersShixinAll t")
public class TCreepersShixinAll implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_SHIXIN_ALL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_SHIXIN_ALL")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_SHIXIN_ALL_ID_GENERATOR")
 private  long id;

@Column(name = "BAD_RECORD")
 private  String badRecord;

 private  String code;

@Column(name = "DIS_RECORD")
 private  String disRecord;

@Column(name = "GOOD_RECORD")
 private  String goodRecord;

 private  String memo;

 private  String name;

 private  String province;

public TCreepersShixinAll() {
}
public void setName(String name){
    this.name = name;
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


public long getId(){
    return this.id;
}


public void setGoodRecord(String goodRecord){
    this.goodRecord = goodRecord;
}


public String getBadRecord(){
    return this.badRecord;
}


public void setMemo(String memo){
    this.memo = memo;
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


public void setBadRecord(String badRecord){
    this.badRecord = badRecord;
}


public String getDisRecord(){
    return this.disRecord;
}


public String getProvince(){
    return this.province;
}


public void setId(long id){
    this.id = id;
}


public String getCode(){
    return this.code;
}


}