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
@Table(name = "T_CREEPERS_ENT_WEB")
@NamedQuery(name = "TCreepersEntWeb.findAll", query = "SELECT t FROM TCreepersEntWeb t")
public class TCreepersEntWeb {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_ENT_WEB_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ENT_WEB")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ENT_WEB_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(length = 100)
 private  String name;

@Column(name = "\"TYPE\"", length = 50)
 private  String type;

@Column(length = 300)
 private  String url;

public TCreepersEntWeb() {
}
public void setName(String name){
    this.name = name;
}


public String getUrl(){
    return this.url;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getName(){
    return this.name;
}


public String getType(){
    return this.type;
}


public String getMerNo(){
    return this.merNo;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return this.id;
}


public void setType(String type){
    this.type = type;
}


public void setUrl(String url){
    this.url = url;
}


}