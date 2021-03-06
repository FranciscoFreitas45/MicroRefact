package com.wxcrm.weixin.pojo;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "lz_wei_article_message", catalog = "wxcrm")
public class LzWeiArticleMessage {

 private  Integer wamId;

 private  String wamTitle;

 private  String wamDescription;

 private  String wamPicurl;

 private  String wamUrl;

 private  String wamStatus;

 private  String wamDesc;

 private  Integer wamRegistor;

 private  String wamRegistdate;

 private  long serialVersionUID;

// Constructors
/**
 * default constructor
 */
public LzWeiArticleMessage() {
}/**
 * full constructor
 */
public LzWeiArticleMessage(String wamTitle, String wamDescription, String wamPicurl, String wamUrl, String wamStatus, String wamDesc, Integer wamRegistor, String wamRegistdate) {
    this.wamTitle = wamTitle;
    this.wamDescription = wamDescription;
    this.wamPicurl = wamPicurl;
    this.wamUrl = wamUrl;
    this.wamStatus = wamStatus;
    this.wamDesc = wamDesc;
    this.wamRegistor = wamRegistor;
    this.wamRegistdate = wamRegistdate;
}
@Column(name = "WAM_PICURL", length = 200)
public String getWamPicurl(){
    return this.wamPicurl;
}


public void setWamPicurl(String wamPicurl){
    this.wamPicurl = wamPicurl;
}


public void setWamRegistor(Integer wamRegistor){
    this.wamRegistor = wamRegistor;
}


@Column(name = "WAM_REGISTDATE", length = 19)
public String getWamRegistdate(){
    return this.wamRegistdate;
}


@Column(name = "WAM_DESC", length = 200)
public String getWamDesc(){
    return this.wamDesc;
}


@Transient
public long getSerialversionuid(){
    return serialVersionUID;
}


public void setWamUrl(String wamUrl){
    this.wamUrl = wamUrl;
}


public void setWamStatus(String wamStatus){
    this.wamStatus = wamStatus;
}


public void setWamRegistdate(String wamRegistdate){
    this.wamRegistdate = wamRegistdate;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WAM_ID", unique = true, nullable = false)
public Integer getWamId(){
    return this.wamId;
}


@Column(name = "WAM_URL", length = 200)
public String getWamUrl(){
    return this.wamUrl;
}


public void setWamDescription(String wamDescription){
    this.wamDescription = wamDescription;
}


@Column(name = "WAM_REGISTOR")
public Integer getWamRegistor(){
    return this.wamRegistor;
}


@Column(name = "WAM_STATUS", length = 20)
public String getWamStatus(){
    return this.wamStatus;
}


@Column(name = "WAM_DESCRIPTION", length = 200)
public String getWamDescription(){
    return this.wamDescription;
}


@Column(name = "WAM_TITLE", length = 200)
public String getWamTitle(){
    return this.wamTitle;
}


public void setWamDesc(String wamDesc){
    this.wamDesc = wamDesc;
}


public void setWamId(Integer wamId){
    this.wamId = wamId;
}


public void setWamTitle(String wamTitle){
    this.wamTitle = wamTitle;
}


}