package com.wxcrm.DTO;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
public class WcShopAdmin {

 private  long serialVersionUID;

 private  Integer wsaId;

 private  String wsaUsername;

 private  String wsaPwd;

 private  String wsaName;

 private  String wsaSex;

 private  Integer wsaRegistor;

 private  String wsaRegistdate;

 private  String wsaLogindate;

 private  String wsaStatus;

 private  String wsaPwdMd5;

 private  String currentPage;

 private  String pageSize;

 private  String[] wsaIds;

 private  String[] roleIds2;

 private  String[] roleIds;

 private  String menuIds;

 private  String wsaUsername_Q;

 private  String wsaName_Q;

// Constructors
/**
 * default constructor
 */
public WcShopAdmin() {
}/**
 * minimal constructor
 */
public WcShopAdmin(String wsaUsername) {
    this.wsaUsername = wsaUsername;
}/**
 * full constructor
 */
public WcShopAdmin(String wsaUsername, String wsaPwd, String wsaName, String wsaSex, Integer wsaRegistor, String wsaRegistdate, String wsaLogindate, String wsaStatus) {
    this.wsaUsername = wsaUsername;
    this.wsaPwd = wsaPwd;
    this.wsaName = wsaName;
    this.wsaSex = wsaSex;
    this.wsaRegistor = wsaRegistor;
    this.wsaRegistdate = wsaRegistdate;
    this.wsaLogindate = wsaLogindate;
    this.wsaStatus = wsaStatus;
}
@Transient
public String[] getWsaIds(){
    return wsaIds;
}


@Column(name = "WSA_PWD", length = 80)
public String getWsaPwd(){
    return this.wsaPwd;
}


@Transient
public String getWsaName_Q(){
    return wsaName_Q;
}


@Column(name = "WSA_REGISTDATE", length = 19)
public String getWsaRegistdate(){
    return this.wsaRegistdate;
}


@Column(name = "WSA_REGISTOR")
public Integer getWsaRegistor(){
    return this.wsaRegistor;
}


@Column(name = "WSA_USERNAME", nullable = false, length = 20)
public String getWsaUsername(){
    return this.wsaUsername;
}


@Transient
public String getMenuIds(){
    return menuIds;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


@Column(name = "WSA_LOGINDATE", length = 19)
public String getWsaLogindate(){
    return this.wsaLogindate;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WSA_ID", unique = true, nullable = false)
public Integer getWsaId(){
    return this.wsaId;
}


@Transient
public String getWsaUsername_Q(){
    return wsaUsername_Q;
}


@Transient
public String getWsaPwdMd5(){
    return wsaPwdMd5;
}


@Column(name = "WSA_NAME", length = 200)
public String getWsaName(){
    return this.wsaName;
}


@Column(name = "WSA_STATUS", length = 20)
public String getWsaStatus(){
    return this.wsaStatus;
}


@Transient
public String getPageSize(){
    return pageSize;
}


@Transient
public String[] getRoleIds(){
    return roleIds;
}


@Transient
public long getSerialVersionUID(){
    return serialVersionUID;
}


@Transient
public String[] getRoleIds2(){
    return roleIds2;
}


@Column(name = "WSA_SEX", length = 1)
public String getWsaSex(){
    return this.wsaSex;
}


}