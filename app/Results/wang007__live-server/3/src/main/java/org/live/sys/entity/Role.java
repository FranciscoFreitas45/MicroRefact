package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
// 表名的命名: 模块名_实体名
@Table(name = "sys_role")
public class Role extends BaseEntity{

 private  long serialVersionUID;

@Column
 private  String roleName;

@Column
 private  Integer serialNo;

@Column
 private  String roleValue;

@Column
 private  String description;

@Column
 private  int isEnable;


public String getRoleName(){
    return roleName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public String getRoleValue(){
    return roleValue;
}


public void setRoleValue(String roleValue){
    this.roleValue = roleValue;
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