package com.wxcrm.goods;
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
@Table(name = "wc_goods_fenlei", catalog = "wxcrm")
public class WcGoodsFenlei {

 private  Integer wgfId;

 private  String wgfName;

 private  Integer wgfWcsId;

 private  String wgfStatus;

 private  Integer wgfRegistor;

 private  String wgfRegistdate;

 private  String currentPage;

 private  String pageSize;

 private  String[] wgfIds;

// Constructors
/**
 * default constructor
 */
public WcGoodsFenlei() {
}/**
 * minimal constructor
 */
public WcGoodsFenlei(String wgfName, Integer wgfWcsId) {
    this.wgfName = wgfName;
    this.wgfWcsId = wgfWcsId;
}/**
 * full constructor
 */
public WcGoodsFenlei(String wgfName, Integer wgfWcsId, String wgfStatus, Integer wgfRegistor, String wgfRegistdate) {
    this.wgfName = wgfName;
    this.wgfWcsId = wgfWcsId;
    this.wgfStatus = wgfStatus;
    this.wgfRegistor = wgfRegistor;
    this.wgfRegistdate = wgfRegistdate;
}
@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WGF_ID", unique = true, nullable = false)
public Integer getWgfId(){
    return this.wgfId;
}


public void setWgfId(Integer wgfId){
    this.wgfId = wgfId;
}


public void setWgfWcsId(Integer wgfWcsId){
    this.wgfWcsId = wgfWcsId;
}


public void setCurrentPage(String currentPage){
    this.currentPage = currentPage;
}


public void setWgfRegistdate(String wgfRegistdate){
    this.wgfRegistdate = wgfRegistdate;
}


@Column(name = "WGF_WCS_ID", nullable = false)
public Integer getWgfWcsId(){
    return this.wgfWcsId;
}


@Transient
public String getPageSize(){
    return pageSize;
}


@Column(name = "WGF_NAME", nullable = false, length = 200)
public String getWgfName(){
    return this.wgfName;
}


public void setWgfIds(String[] wgfIds){
    this.wgfIds = wgfIds;
}


@Column(name = "WGF_REGISTOR")
public Integer getWgfRegistor(){
    return this.wgfRegistor;
}


public void setWgfName(String wgfName){
    this.wgfName = wgfName;
}


@Column(name = "WGF_STATUS")
public String getWgfStatus(){
    return this.wgfStatus;
}


@Column(name = "WGF_REGISTDATE", length = 19)
public String getWgfRegistdate(){
    return this.wgfRegistdate;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


@Transient
public String[] getWgfIds(){
    return wgfIds;
}


public void setWgfStatus(String wgfStatus){
    this.wgfStatus = wgfStatus;
}


public void setPageSize(String pageSize){
    this.pageSize = pageSize;
}


public void setWgfRegistor(Integer wgfRegistor){
    this.wgfRegistor = wgfRegistor;
}


}