package com.ipe.module.core.entity;
 import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import javax.persistence;
@Table(name = "t_cor_config", schema = "", catalog = "db_work")
@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SysConfig extends IDEntity{

 private  String key;

 private  String value;

 private  String remark;

 private  String type;

 private  Integer sno;


public void setSno(Integer sno){
    this.sno = sno;
}


@Column(name = "key_", nullable = false)
@Basic
public String getKey(){
    return key;
}


@Column(name = "value_", nullable = false)
@Basic
public String getValue(){
    return value;
}


@Column(name = "type_", nullable = false)
@Basic
public String getType(){
    return type;
}


public void setRemark(String remark){
    this.remark = remark;
}


@OrderBy(value = "sno")
@Column(name = "sno")
@Basic
public Integer getSno(){
    return sno;
}


public void setValue(String value){
    this.value = value;
}


@Column(name = "remark", nullable = false)
@Basic
public String getRemark(){
    return remark;
}


public void setType(String type){
    this.type = type;
}


public void setKey(String key){
    this.key = key;
}


}