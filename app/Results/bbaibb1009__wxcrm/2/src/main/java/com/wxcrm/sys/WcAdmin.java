package com.wxcrm.sys;
 import javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "WC_ADMIN", catalog = "wxcrm")
public class WcAdmin {

 private  Integer wadId;

 private  String wadUsername;

 private  String wadPwd;

 private  String wadName;

 private  String wadSex;

 private  Integer wadRegistor;

 private  String wadRegistdate;

 private  String wadPwdMd5;

 private  boolean remember;

 private  String[] roleIds;

 private  String currentPage;

 private  String pageSize;

 private  String[] adminIds;

 private  String[] roleIds2;

 private  String menuIds;

 private  String wadLogindate;

 private  String wadUsername_Q;

 private  String wadName_Q;

// Constructors
/**
 * default constructor
 */
public WcAdmin() {
}/**
 * minimal constructor
 */
public WcAdmin(Integer wadId, String wadUsername) {
    this.wadId = wadId;
    this.wadUsername = wadUsername;
}/**
 * full constructor
 */
public WcAdmin(Integer wadId, String wadUsername, String wadPwd, String wadName, String wadSex, Integer wadRegistor, String wadRegistdate) {
    this.wadId = wadId;
    this.wadUsername = wadUsername;
    this.wadPwd = wadPwd;
    this.wadName = wadName;
    this.wadSex = wadSex;
    this.wadRegistor = wadRegistor;
    this.wadRegistdate = wadRegistdate;
}
public void setWadRegistor(Integer wadRegistor){
    this.wadRegistor = wadRegistor;
}


@Transient
public boolean isRemember(){
    return remember;
}


public void setWadRegistdate(String wadRegistdate){
    this.wadRegistdate = wadRegistdate;
}


@Transient
public String getWadName_Q(){
    return wadName_Q;
}


@Transient
public String getWadUsername_Q(){
    return wadUsername_Q;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WAD_ID", unique = true, nullable = false)
public Integer getWadId(){
    return this.wadId;
}


@Transient
public String[] getAdminIds(){
    return adminIds;
}


public void setWadSex(String wadSex){
    this.wadSex = wadSex;
}


@Column(name = "WAD_LOGINDATE", length = 19)
public String getWadLogindate(){
    return wadLogindate;
}


@Column(name = "WAD_SEX", length = 1)
public String getWadSex(){
    return this.wadSex;
}


@Transient
public String getMenuIds(){
    return menuIds;
}


public void setWadUsername(String wadUsername){
    this.wadUsername = wadUsername;
}


@Column(name = "WAD_PWD", length = 80)
public String getWadPwd(){
    return this.wadPwd;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


public void setWadUsername_Q(String wadUsername_Q){
    this.wadUsername_Q = wadUsername_Q;
}


public void setWadName_Q(String wadName_Q){
    this.wadName_Q = wadName_Q;
}


@Column(name = "WAD_REGISTDATE", length = 19)
public String getWadRegistdate(){
    return this.wadRegistdate;
}


@Column(name = "WAD_USERNAME", length = 20)
public String getWadUsername(){
    return this.wadUsername;
}


public void setAdminIds(String[] adminIds){
    this.adminIds = adminIds;
}


public void setWadPwd(String wadPwd){
    this.wadPwd = wadPwd;
}


public void setWadPwdMd5(String wadPwdMd5){
    this.wadPwdMd5 = wadPwdMd5;
}


public void setCurrentPage(String currentPage){
    this.currentPage = currentPage;
}


public void setRoleIds(String[] roleIds){
    this.roleIds = roleIds;
}


@Column(name = "WAD_NAME", length = 200)
public String getWadName(){
    return this.wadName;
}


@Transient
public String getPageSize(){
    return pageSize;
}


@Transient
public String[] getRoleIds(){
    return roleIds;
}


public void setWadName(String wadName){
    this.wadName = wadName;
}


public void setMenuIds(String menuIds){
    this.menuIds = menuIds;
}


public void setWadId(Integer wadId){
    this.wadId = wadId;
}


@Column(name = "WAD_REGISTOR")
public Integer getWadRegistor(){
    return this.wadRegistor;
}


@Transient
public String[] getRoleIds2(){
    return roleIds2;
}


@Transient
public String getWadPwdMd5(){
    return wadPwdMd5;
}


public void setWadLogindate(String wadLogindate){
    this.wadLogindate = wadLogindate;
}


public void setRoleIds2(String[] roleIds2){
    this.roleIds2 = roleIds2;
}


public void setPageSize(String pageSize){
    this.pageSize = pageSize;
}


public void setRemember(boolean remember){
    this.remember = remember;
}


}