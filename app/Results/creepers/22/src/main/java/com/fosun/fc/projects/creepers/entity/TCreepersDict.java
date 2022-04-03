package com.fosun.fc.projects.creepers.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "T_CREEPERS_DICT")
@NamedQuery(name = "TCreepersDict.findAll", query = "SELECT t FROM TCreepersDict t")
public class TCreepersDict {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_DICT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_DICT")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_DICT_ID_GENERATOR")
 private  Long id;

 private  String category;

@Temporal(TemporalType.DATE)
@Column(name = "END_DT")
 private  Date endDt;

 private  String key1;

 private  String key2;

 private  String memo;

@Temporal(TemporalType.DATE)
@Column(name = "START_DT")
 private  Date startDt;

 private  String value1;

 private  String value2;

public TCreepersDict() {
}
public Date getStartDt(){
    return this.startDt;
}


public void setEndDt(Date endDt){
    this.endDt = endDt;
}


public String getCategory(){
    return this.category;
}


public String getKey2(){
    return this.key2;
}


public String getKey1(){
    return this.key1;
}


public Long getId(){
    return this.id;
}


public String getValue1(){
    return this.value1;
}


public String getValue2(){
    return this.value2;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public void setValue2(String value2){
    this.value2 = value2;
}


public void setValue1(String value1){
    this.value1 = value1;
}


public void setCategory(String category){
    this.category = category;
}


public Date getEndDt(){
    return this.endDt;
}


public void setKey1(String key1){
    this.key1 = key1;
}


public void setId(Long id){
    this.id = id;
}


public void setKey2(String key2){
    this.key2 = key2;
}


public void setStartDt(Date startDt){
    this.startDt = startDt;
}


}