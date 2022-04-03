package org.live.DTO;
 import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
public class Role extends BaseEntity{

 private  long serialVersionUID;

 private  String roleName;

 private  Integer serialNo;

 private  String roleValue;

 private  String description;

 private  int isEnable;


public String getRoleName(){
    return roleName;
}


public String getRoleValue(){
    return roleValue;
}


public void setSerialNo(Integer serialNo){
    this.serialNo = serialNo;
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