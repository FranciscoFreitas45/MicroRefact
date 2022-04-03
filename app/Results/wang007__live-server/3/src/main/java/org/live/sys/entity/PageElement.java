package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import org.live.Request.MenuRequest;
import org.live.Request.Impl.MenuRequestImpl;
import org.live.DTO.Menu;
import org.live.Request.PermissionRequest;
import org.live.Request.Impl.PermissionRequestImpl;
import org.live.DTO.Permission;
@Entity
@Table(name = "sys_pageelement")
public class PageElement extends BaseEntity{

 private  long serialVersionUID;

@Transient
 private  Menu menu;

@Transient
 private  Permission permission;

@Column
 private  String elementName;

@Column
 private  Integer serialNo;

@Column
 private  String description;

@Column(name = "idVM7Z")
 private String idVM7Z;

@Transient
 private MenuRequest menurequest = new MenuRequestImpl();;

@Column(name = "idIJY1")
 private String idIJY1;

@Transient
 private PermissionRequest permissionrequest = new PermissionRequestImpl();;


public Permission getPermission(){
  this.permission = permissionrequest.getPermission(this.idIJY1);
return this.permission;
}}



public void setElementName(String elementName){
    this.elementName = elementName;
}


public Menu getMenu(){
  this.menu = menurequest.getMenu(this.idVM7Z);
return this.menu;
}}



public void setPermission(Permission permission){
this.idIJY1 = permission.getPermission() ;
permissionrequest.setPermission(permission,this.idIJY1);
 this.permission = permission;
}



public void setSerialNo(Integer serialNo){
    this.serialNo = serialNo;
}


public void setDescription(String description){
    this.description = description;
}


public void setMenu(Menu menu){
this.idVM7Z = menu.getMenu() ;
menurequest.setMenu(menu,this.idVM7Z);
 this.menu = menu;
}



public String getDescription(){
    return description;
}


public String getElementName(){
    return elementName;
}


public Integer getSerialNo(){
    return serialNo;
}


}