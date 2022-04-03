package org.live.sys.entity;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "sys_group")
public class Group extends BaseEntity{

 private  long serialVersionUID;

@Column
 private  String groupName;

@Column
 private  Integer serialNo;

@Column
 private  String description;

@Column
 private  int isEnable;


public String getGroupName(){
    return groupName;
}


public void setGroupName(String groupName){
    this.groupName = groupName;
}


public void setSerialNo(Integer serialNo){
    this.serialNo = serialNo;
}


public void setDescription(String description){
    this.description = description;
}


public void setIsEnable(int isEnable){
    this.isEnable = isEnable;
}


public String getDescription(){
    return description;
}


public Integer getSerialNo(){
    return serialNo;
}


public int getIsEnable(){
    return isEnable;
}


}