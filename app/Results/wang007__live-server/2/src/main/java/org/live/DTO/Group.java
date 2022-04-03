package org.live.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
public class Group extends BaseEntity{

 private  long serialVersionUID;

 private  String groupName;

 private  Integer serialNo;

 private  String description;

 private  int isEnable;


public String getGroupName(){
    return groupName;
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