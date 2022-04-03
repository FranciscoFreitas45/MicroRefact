package com.zis.requirement.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "sys_var")
public class SysVar {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "depKey", length = 100)
 private  String depKey;

@Column(name = "depValue")
 private  Integer depValue;

// Constructors
/**
 * default constructor
 */
public SysVar() {
}/**
 * full constructor
 */
public SysVar(String depKey, Integer depValue) {
    this.depKey = depKey;
    this.depValue = depValue;
}
public void setDepValue(Integer depValue){
    this.depValue = depValue;
}


public Integer getDepValue(){
    return this.depValue;
}


public void setDepKey(String depKey){
    this.depKey = depKey;
}


public void setId(Integer id){
    this.id = id;
}


public String getDepKey(){
    return this.depKey;
}


public Integer getId(){
    return this.id;
}


}