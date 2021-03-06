package com.wxcrm.sys;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "wc_menu", catalog = "wxcrm")
public class WcMenu {

 private  long serialVersionUID;

 private  Integer wmeId;

 private  String wmeName;

 private  String wmeUrl;

 private  String wmeLevel;

 private  Integer wmeOrder;

 private  String wmeDesc;

 private  Integer wmeParentId;

 private  Integer wmeRegistor;

 private  String wmeRegistDate;

 private  String wmeName_Q;

 private  String wmeLevel_Q;

 private  String currentPage;

 private  String pageSize;

 private  String[] menuIds;

// Constructors
/**
 * default constructor
 */
public WcMenu() {
}/**
 * minimal constructor
 */
public WcMenu(String wmeName) {
    this.wmeName = wmeName;
}/**
 * full constructor
 */
public WcMenu(String wmeName, String wmeUrl, String wmeLevel, Integer wmeOrder, String wmeDesc, Integer wmeParentId, Integer wmeRegistor, String wmeRegistDate) {
    this.wmeName = wmeName;
    this.wmeUrl = wmeUrl;
    this.wmeLevel = wmeLevel;
    this.wmeOrder = wmeOrder;
    this.wmeDesc = wmeDesc;
    this.wmeParentId = wmeParentId;
    this.wmeRegistor = wmeRegistor;
    this.wmeRegistDate = wmeRegistDate;
}
public void setWmeName(String wmeName){
    this.wmeName = wmeName;
}


@Column(name = "WME_ORDER")
public Integer getWmeOrder(){
    return this.wmeOrder;
}


public void setWmeRegistor(Integer wmeRegistor){
    this.wmeRegistor = wmeRegistor;
}


@Transient
public String getWmeName_Q(){
    return wmeName_Q;
}


@Column(name = "WME_URL", length = 200)
public String getWmeUrl(){
    return this.wmeUrl;
}


@Transient
public String getWmeLevel_Q(){
    return wmeLevel_Q;
}


@Column(name = "WME_NAME", nullable = false, length = 50)
public String getWmeName(){
    return this.wmeName;
}


@Column(name = "WME_DESC", length = 400)
public String getWmeDesc(){
    return this.wmeDesc;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WME_ID", unique = true, nullable = false)
public Integer getWmeId(){
    return this.wmeId;
}


public void setWmeLevel(String wmeLevel){
    this.wmeLevel = wmeLevel;
}


public void setWmeDesc(String wmeDesc){
    this.wmeDesc = wmeDesc;
}


@Column(name = "WME_PARENT_ID")
public Integer getWmeParentId(){
    return this.wmeParentId;
}


@Column(name = "WME_REGIST_DATE", length = 19)
public String getWmeRegistDate(){
    return this.wmeRegistDate;
}


@Transient
public String[] getMenuIds(){
    return menuIds;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


public void setWmeName_Q(String wmeName_Q){
    this.wmeName_Q = wmeName_Q;
}


public void setWmeId(Integer wmeId){
    this.wmeId = wmeId;
}


public void setWmeParentId(Integer wmeParentId){
    this.wmeParentId = wmeParentId;
}


public void setCurrentPage(String currentPage){
    this.currentPage = currentPage;
}


public void setWmeOrder(Integer wmeOrder){
    this.wmeOrder = wmeOrder;
}


public void setWmeLevel_Q(String wmeLevel_Q){
    this.wmeLevel_Q = wmeLevel_Q;
}


public void setWmeUrl(String wmeUrl){
    this.wmeUrl = wmeUrl;
}


@Column(name = "WME_LEVEL", length = 1)
public String getWmeLevel(){
    return this.wmeLevel;
}


@Column(name = "WME_REGISTOR")
public Integer getWmeRegistor(){
    return this.wmeRegistor;
}


@Transient
public String getPageSize(){
    return pageSize;
}


public void setMenuIds(String[] menuIds){
    this.menuIds = menuIds;
}


@Transient
public long getSerialVersionUID(){
    return serialVersionUID;
}


public void setWmeRegistDate(String wmeRegistDate){
    this.wmeRegistDate = wmeRegistDate;
}


public void setPageSize(String pageSize){
    this.pageSize = pageSize;
}


}