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
@Table(name = "wc_goods", catalog = "wxcrm")
public class WcGoods {

 private  Integer wgsId;

 private  String wgsName;

 private  Integer wgsWgfId;

 private  Integer wgsWcsId;

 private  Double wgsBzPrice;

 private  Double wgsLsPrice;

 private  Double wgsKucun;

 private  String wgsStatus;

 private  Integer wgsRegistor;

 private  String wgsRegistdate;

 private  String currentPage;

 private  String pageSize;

 private  String[] wgsIds;

 private  String wgfName;

// Constructors
/**
 * default constructor
 */
public WcGoods() {
}/**
 * minimal constructor
 */
public WcGoods(String wgsName, Integer wgsWgfId, Integer wgsWcsId) {
    this.wgsName = wgsName;
    this.wgsWgfId = wgsWgfId;
    this.wgsWcsId = wgsWcsId;
}/**
 * full constructor
 */
public WcGoods(String wgsName, Integer wgsWgfId, Integer wgsWcsId, Double wgsBzPrice, Double wgsLsPrice, Double wgsKucun, String wgsStatus, Integer wgsRegistor, String wgsRegistdate) {
    this.wgsName = wgsName;
    this.wgsWgfId = wgsWgfId;
    this.wgsWcsId = wgsWcsId;
    this.wgsBzPrice = wgsBzPrice;
    this.wgsLsPrice = wgsLsPrice;
    this.wgsKucun = wgsKucun;
    this.wgsStatus = wgsStatus;
    this.wgsRegistor = wgsRegistor;
    this.wgsRegistdate = wgsRegistdate;
}
@Column(name = "WGS_REGISTOR")
public Integer getWgsRegistor(){
    return this.wgsRegistor;
}


@Transient
public String[] getWgsIds(){
    return wgsIds;
}


public void setWgsKucun(Double wgsKucun){
    this.wgsKucun = wgsKucun;
}


@Column(name = "WGS_STATUS", length = 20)
public String getWgsStatus(){
    return this.wgsStatus;
}


public void setWgsName(String wgsName){
    this.wgsName = wgsName;
}


public void setWgsLsPrice(Double wgsLsPrice){
    this.wgsLsPrice = wgsLsPrice;
}


public void setWgsRegistor(Integer wgsRegistor){
    this.wgsRegistor = wgsRegistor;
}


@Column(name = "WGS_LS_PRICE", precision = 11)
public Double getWgsLsPrice(){
    return this.wgsLsPrice;
}


@Transient
public String getWgfName(){
    return wgfName;
}


public void setWgsRegistdate(String wgsRegistdate){
    this.wgsRegistdate = wgsRegistdate;
}


@Column(name = "WGS_NAME", nullable = false, length = 200)
public String getWgsName(){
    return this.wgsName;
}


public void setWgsWcsId(Integer wgsWcsId){
    this.wgsWcsId = wgsWcsId;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


@Column(name = "WGS_KUCUN", precision = 11, scale = 3)
public Double getWgsKucun(){
    return this.wgsKucun;
}


@Column(name = "WGS_BZ_PRICE", precision = 11)
public Double getWgsBzPrice(){
    return this.wgsBzPrice;
}


public void setWgsWgfId(Integer wgsWgfId){
    this.wgsWgfId = wgsWgfId;
}


@Column(name = "WGS_WCS_ID", nullable = false)
public Integer getWgsWcsId(){
    return this.wgsWcsId;
}


@Column(name = "WGS_REGISTDATE", length = 19)
public String getWgsRegistdate(){
    return this.wgsRegistdate;
}


public void setCurrentPage(String currentPage){
    this.currentPage = currentPage;
}


public void setWgsIds(String[] wgsIds){
    this.wgsIds = wgsIds;
}


public void setWgsBzPrice(Double wgsBzPrice){
    this.wgsBzPrice = wgsBzPrice;
}


@Transient
public String getPageSize(){
    return pageSize;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WGS_ID", unique = true, nullable = false)
public Integer getWgsId(){
    return this.wgsId;
}


public void setWgsId(Integer wgsId){
    this.wgsId = wgsId;
}


public void setWgfName(String wgfName){
    this.wgfName = wgfName;
}


public void setWgsStatus(String wgsStatus){
    this.wgsStatus = wgsStatus;
}


@Column(name = "WGS_WGF_ID", nullable = false)
public Integer getWgsWgfId(){
    return this.wgsWgfId;
}


public void setPageSize(String pageSize){
    this.pageSize = pageSize;
}


}