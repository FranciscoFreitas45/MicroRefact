package com.zis.bookinfo.bean;
 import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
@Entity
@Table(name = "bookinfo_detail")
public class BookinfoDetail {

 private  long serialVersionUID;

@Id
@Column(name = "bookid")
 private  Integer bookid;

@Column(name = "image_url")
 private  String imageUrl;

@Column(name = "taobao_title")
 private  String taobaoTitle;

@Column(name = "taobao_catagory_id")
 private  Integer taobaoCatagoryId;

@Column(name = "taobao_forbidden")
 private  Boolean taobaoForbidden;

@Column(name = "summary", length = 8192)
 private  String summary;

@Column(name = "catalog", length = 8192)
 private  String catalog;

@Column(name = "out_id")
 private  Integer outId;

 private  String source;

@Column(name = "GMT_CREATE", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "GMT_MODIFY")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public BookinfoDetail() {
}/**
 * minimal constructor
 */
public BookinfoDetail(Integer bookid, Timestamp gmtCreate, Timestamp gmtModify, Integer version) {
    this.bookid = bookid;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}/**
 * full constructor
 */
public BookinfoDetail(Integer bookid, String imageUrl, String taobaoTitle, Integer taobaoCatagoryId, String summary, String catalog, Timestamp gmtCreate, Timestamp gmtModify, Integer version) {
    this.bookid = bookid;
    this.imageUrl = imageUrl;
    this.taobaoTitle = taobaoTitle;
    this.taobaoCatagoryId = taobaoCatagoryId;
    this.summary = summary;
    this.catalog = catalog;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}
public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


public void setSource(String source){
    this.source = source;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public void setCatalog(String catalog){
    this.catalog = catalog;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public Boolean getTaobaoForbidden(){
    return taobaoForbidden;
}


public void setTaobaoTitle(String taobaoTitle){
    this.taobaoTitle = taobaoTitle;
}


public String getCatalog(){
    return this.catalog;
}


public void setBookid(Integer bookid){
    this.bookid = bookid;
}


public String getImageUrl(){
    return this.imageUrl;
}


public Integer getBookid(){
    return this.bookid;
}


public void setTaobaoCatagoryId(Integer taobaoCatagoryId){
    this.taobaoCatagoryId = taobaoCatagoryId;
}


public Integer getTaobaoCatagoryId(){
    return this.taobaoCatagoryId;
}


public Integer getVersion(){
    return this.version;
}


public void setSummary(String summary){
    this.summary = summary;
}


public String getSummary(){
    return this.summary;
}


public Integer getOutId(){
    return outId;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setTaobaoForbidden(Boolean taobaoForbidden){
    this.taobaoForbidden = taobaoForbidden;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public String getTaobaoTitle(){
    return this.taobaoTitle;
}


public String getSource(){
    return source;
}


public void setOutId(Integer outId){
    this.outId = outId;
}


@Override
public String toString(){
    return "BookinfoDetail [bookid=" + bookid + ", imageUrl=" + imageUrl + ", taobaoTitle=" + taobaoTitle + ", taobaoCatagoryId=" + taobaoCatagoryId + ", taobaoForbidden=" + taobaoForbidden + ", summary=" + summary + ", catalog=" + catalog + ", outId=" + outId + ", source=" + source + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", version=" + version + "]";
}


}