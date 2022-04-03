package com.uec.imonitor.config.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "config_tenant_params")
public class ConfigTenantParamEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "innerid", nullable = false)
 private  Integer innerid;

@Column(name = "param_id")
 private  Integer paramId;

@Column(name = "tenant_id")
 private  Integer tenantId;

@Column(name = "param_name")
 private  String paramName;

@Column(name = "value")
 private  String value;


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public String getValue(){
    return value;
}


public Integer getParamId(){
    return paramId;
}


public String getParamName(){
    return paramName;
}


public void setParamName(String paramName){
    this.paramName = paramName;
}


public void setParamId(Integer paramId){
    this.paramId = paramId;
}


public Integer getInnerid(){
    return innerid;
}


public void setTenantId(Integer tenantId){
    this.tenantId = tenantId;
}


public void setValue(String value){
    this.value = value;
}


public Integer getTenantId(){
    return tenantId;
}


}