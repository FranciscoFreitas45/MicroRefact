package com.uec.imonitor.config.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "config_page_params")
public class ConfigPageParamEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "param_id", nullable = false)
 private  Integer paramId;

@Column(name = "name")
 private  String name;

@Column(name = "display_name")
 private  String displayName;

@Column(name = "value")
 private  String value;

@Column(name = "description")
 private  String description;


public void setName(String name){
    this.name = name;
}


public String getValue(){
    return value;
}


public Integer getParamId(){
    return paramId;
}


public String getName(){
    return name;
}


public void setParamId(Integer paramId){
    this.paramId = paramId;
}


public String getDisplayName(){
    return displayName;
}


public void setValue(String value){
    this.value = value;
}


public void setDescription(String description){
    this.description = description;
}


public void setDisplayName(String displayName){
    this.displayName = displayName;
}


public String getDescription(){
    return description;
}


}