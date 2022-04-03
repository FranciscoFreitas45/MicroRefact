package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "sys_permission")
public class Permission extends BaseEntity{

 private  long serialVersionUID;

@Column
 private  int permissionType;

@Column
 private  String permissionValue;

@Column
 private  int isEnable;


public void setPermissionValue(String permissionValue){
    this.permissionValue = permissionValue;
}


public void setIsEnable(int isEnable){
    this.isEnable = isEnable;
}


public void setPermissionType(int permissionType){
    this.permissionType = permissionType;
}


public int getPermissionType(){
    return permissionType;
}


public String getPermissionValue(){
    return permissionValue;
}


public int getIsEnable(){
    return isEnable;
}


}