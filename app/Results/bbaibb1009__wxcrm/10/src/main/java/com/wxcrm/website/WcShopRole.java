package com.wxcrm.website;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
@Entity
@Table(name = "wc_shop_role", catalog = "wxcrm")
public class WcShopRole {

 private  long serialVersionUID;

 private  Integer wsrRoleId;

 private  String wsrRoleName;

 private  String wsrRoleDesc;

 private  Integer wsrRegistor;

 private  String wsrRegistDate;

 private  String roleName_Q;

 private  String currentPage;

 private  String pageSize;

 private  String[] wsrRoleIds;

 private  String menuIds;

 private  String adminIds;

// Constructors
/**
 * default constructor
 */
public WcShopRole() {
}/**
 * minimal constructor
 */
public WcShopRole(String wsrRoleName) {
    this.wsrRoleName = wsrRoleName;
}/**
 * full constructor
 */
public WcShopRole(String wsrRoleName, String wsrRoleDesc, Integer wsrRegistor, String wsrRegistDate) {
    this.wsrRoleName = wsrRoleName;
    this.wsrRoleDesc = wsrRoleDesc;
    this.wsrRegistor = wsrRegistor;
    this.wsrRegistDate = wsrRegistDate;
}
public void setWsrRoleName(String wsrRoleName){
    this.wsrRoleName = wsrRoleName;
}


public void setAdminIds(String adminIds){
    this.adminIds = adminIds;
}


public void setWsrRegistDate(String wsrRegistDate){
    this.wsrRegistDate = wsrRegistDate;
}


public void setRoleName_Q(String roleName_Q){
    this.roleName_Q = roleName_Q;
}


public void setCurrentPage(String currentPage){
    this.currentPage = currentPage;
}


@Transient
public String getAdminIds(){
    return adminIds;
}


public void setWsrRoleDesc(String wsrRoleDesc){
    this.wsrRoleDesc = wsrRoleDesc;
}


@Column(name = "WSR_REGISTOR")
public Integer getWsrRegistor(){
    return this.wsrRegistor;
}


@Column(name = "WSR_ROLE_DESC", length = 200)
public String getWsrRoleDesc(){
    return this.wsrRoleDesc;
}


@Transient
public String[] getWsrRoleIds(){
    return wsrRoleIds;
}


@Transient
public String getRoleName_Q(){
    return roleName_Q;
}


@Column(name = "WSR_ROLE_NAME", nullable = false, length = 100)
public String getWsrRoleName(){
    return this.wsrRoleName;
}


@Transient
public String getMenuIds(){
    return menuIds;
}


@Transient
public String getPageSize(){
    return pageSize;
}


public void setMenuIds(String menuIds){
    this.menuIds = menuIds;
}


public void setWsrRegistor(Integer wsrRegistor){
    this.wsrRegistor = wsrRegistor;
}


@Transient
public long getSerialVersionUID(){
    return serialVersionUID;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WSR_ROLE_ID", unique = true, nullable = false)
public Integer getWsrRoleId(){
    return this.wsrRoleId;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


public void setWsrRoleIds(String[] wsrRoleIds){
    this.wsrRoleIds = wsrRoleIds;
}


@Column(name = "WSR_REGIST_DATE", length = 19)
public String getWsrRegistDate(){
    return this.wsrRegistDate;
}


public void setPageSize(String pageSize){
    this.pageSize = pageSize;
}


public void setWsrRoleId(Integer wsrRoleId){
    this.wsrRoleId = wsrRoleId;
}


}