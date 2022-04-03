package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
@Entity
@Table(name = "sys_operation")
public class Operation extends BaseEntity{

 private  long serialVersionUID;

@ManyToOne
@JoinColumn(name = "menu_id")
 private  Menu menu;

@OneToOne
@JoinColumn(name = "permission_id")
 private  Permission permission;

@Column
 private  String operationName;

@Column
 private  Integer serialNo;

@Column
 private  String description;


public Permission getPermission(){
    return permission;
}


public String getOperationName(){
    return operationName;
}


public Menu getMenu(){
    return menu;
}


public void setPermission(Permission permission){
    this.permission = permission;
}


public void setSerialNo(Integer serialNo){
    this.serialNo = serialNo;
}


public void setDescription(String description){
    this.description = description;
}


public void setMenu(Menu menu){
    this.menu = menu;
}


public String getDescription(){
    return description;
}


public void setOperationName(String operationName){
    this.operationName = operationName;
}


public Integer getSerialNo(){
    return serialNo;
}


}