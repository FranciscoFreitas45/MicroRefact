package com.wxcrm.weixin;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "LZ_WEI_ERRORCODE", schema = "dbo", catalog = "oilchem")
public class LzWeiErrorcode {

 private  Integer waeId;

 private  String waeCode;

 private  String waeDesc;

 private  String currentPage;

 private  String pageSize;

// Constructors
/**
 * default constructor
 */
public LzWeiErrorcode() {
}/**
 * minimal constructor
 */
public LzWeiErrorcode(Integer waeId) {
    this.waeId = waeId;
}/**
 * full constructor
 */
public LzWeiErrorcode(Integer waeId, String waeCode, String waeDesc) {
    this.waeId = waeId;
    this.waeCode = waeCode;
    this.waeDesc = waeDesc;
}
public void setWaeId(Integer waeId){
    this.waeId = waeId;
}


@Transient
public String getPageSize(){
    return pageSize;
}


public void setWaeDesc(String waeDesc){
    this.waeDesc = waeDesc;
}


@Id
@Column(name = "WAE_ID", unique = true, nullable = false)
public Integer getWaeId(){
    return this.waeId;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


public void setCurrentPage(String currentPage){
    this.currentPage = currentPage;
}


@Column(name = "WAE_CODE", length = 20)
public String getWaeCode(){
    return this.waeCode;
}


@Column(name = "WAE_DESC", length = 400)
public String getWaeDesc(){
    return this.waeDesc;
}


public void setPageSize(String pageSize){
    this.pageSize = pageSize;
}


public void setWaeCode(String waeCode){
    this.waeCode = waeCode;
}


}