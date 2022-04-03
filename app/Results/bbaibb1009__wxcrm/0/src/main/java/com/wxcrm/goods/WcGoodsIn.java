package com.wxcrm.goods;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "wc_goods_in", catalog = "wxcrm")
public class WcGoodsIn {

 private  long serialVersionUID;

 private  Integer wgiId;

 private  Integer wgiWgsId;

 private  Integer wgiWcsId;

 private  String wgiInTime;

 private  Double wgiInNum;

 private  Double wgiInPrice;

 private  Integer wgiInAdmin;

 private  Double wgiSurplusNum;

 private  String wgiLoc;

 private  String wgiDesc;

 private  String wgiStatus;

 private  Integer wgiRegistor;

 private  String wgiRegistdate;

 private  String currentPage;

 private  String pageSize;

 private  String[] wgiIds;

// Constructors
/**
 * default constructor
 */
public WcGoodsIn() {
}/**
 * minimal constructor
 */
public WcGoodsIn(Integer wgiWgsId, Integer wgiWcsId) {
    this.wgiWgsId = wgiWgsId;
    this.wgiWcsId = wgiWcsId;
}/**
 * full constructor
 */
public WcGoodsIn(Integer wgiWgsId, Integer wgiWcsId, String wgiInTime, Double wgiInNum, Double wgiInPrice, Integer wgiInAdmin, Double wgiSurplusNum, String wgiLoc, String wgiDesc, String wgiStatus, Integer wgiRegistor, String wgiRegistdate) {
    this.wgiWgsId = wgiWgsId;
    this.wgiWcsId = wgiWcsId;
    this.wgiInTime = wgiInTime;
    this.wgiInNum = wgiInNum;
    this.wgiInPrice = wgiInPrice;
    this.wgiInAdmin = wgiInAdmin;
    this.wgiSurplusNum = wgiSurplusNum;
    this.wgiLoc = wgiLoc;
    this.wgiDesc = wgiDesc;
    this.wgiStatus = wgiStatus;
    this.wgiRegistor = wgiRegistor;
    this.wgiRegistdate = wgiRegistdate;
}
public void setWgiWgsId(Integer wgiWgsId){
    this.wgiWgsId = wgiWgsId;
}


@Column(name = "WGI_IN_PRICE", precision = 11)
public Double getWgiInPrice(){
    return this.wgiInPrice;
}


@Column(name = "WGI_STATUS", length = 20)
public String getWgiStatus(){
    return this.wgiStatus;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WGI_ID", unique = true, nullable = false)
public Integer getWgiId(){
    return this.wgiId;
}


@Column(name = "WGI_REGISTDATE", length = 19)
public String getWgiRegistdate(){
    return this.wgiRegistdate;
}


public void setWgiWcsId(Integer wgiWcsId){
    this.wgiWcsId = wgiWcsId;
}


public void setWgiInNum(Double wgiInNum){
    this.wgiInNum = wgiInNum;
}


public void setWgiRegistor(Integer wgiRegistor){
    this.wgiRegistor = wgiRegistor;
}


public void setWgiLoc(String wgiLoc){
    this.wgiLoc = wgiLoc;
}


@Transient
public String[] getWgiIds(){
    return wgiIds;
}


public void setWgiInTime(String wgiInTime){
    this.wgiInTime = wgiInTime;
}


public void setWgiInAdmin(Integer wgiInAdmin){
    this.wgiInAdmin = wgiInAdmin;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


public void setWgiIds(String[] wgiIds){
    this.wgiIds = wgiIds;
}


@Column(name = "WGI_IN_TIME", length = 19)
public String getWgiInTime(){
    return this.wgiInTime;
}


@Column(name = "WGI_LOC", length = 100)
public String getWgiLoc(){
    return this.wgiLoc;
}


public void setWgiRegistdate(String wgiRegistdate){
    this.wgiRegistdate = wgiRegistdate;
}


public void setWgiSurplusNum(Double wgiSurplusNum){
    this.wgiSurplusNum = wgiSurplusNum;
}


@Column(name = "WGI_IN_ADMIN")
public Integer getWgiInAdmin(){
    return this.wgiInAdmin;
}


public void setWgiStatus(String wgiStatus){
    this.wgiStatus = wgiStatus;
}


@Column(name = "WGI_REGISTOR")
public Integer getWgiRegistor(){
    return this.wgiRegistor;
}


public void setWgiDesc(String wgiDesc){
    this.wgiDesc = wgiDesc;
}


public void setWgiInPrice(Double wgiInPrice){
    this.wgiInPrice = wgiInPrice;
}


@Column(name = "WGI_SURPLUS_NUM", precision = 11)
public Double getWgiSurplusNum(){
    return this.wgiSurplusNum;
}


public void setCurrentPage(String currentPage){
    this.currentPage = currentPage;
}


@Column(name = "WGI_DESC", length = 200)
public String getWgiDesc(){
    return this.wgiDesc;
}


@Column(name = "WGI_WCS_ID", nullable = false)
public Integer getWgiWcsId(){
    return this.wgiWcsId;
}


@Column(name = "WGI_IN_NUM", precision = 11)
public Double getWgiInNum(){
    return this.wgiInNum;
}


@Transient
public String getPageSize(){
    return pageSize;
}


@Column(name = "WGI_WGS_ID", nullable = false)
public Integer getWgiWgsId(){
    return this.wgiWgsId;
}


@Transient
public long getSerialVersionUID(){
    return serialVersionUID;
}


public void setWgiId(Integer wgiId){
    this.wgiId = wgiId;
}


public void setPageSize(String pageSize){
    this.pageSize = pageSize;
}


}