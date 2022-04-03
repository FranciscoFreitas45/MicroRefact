package org.live.DTO;
 import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
public class Permission extends BaseEntity{

 private  long serialVersionUID;

 private  int permissionType;

 private  String permissionValue;

 private  int isEnable;


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