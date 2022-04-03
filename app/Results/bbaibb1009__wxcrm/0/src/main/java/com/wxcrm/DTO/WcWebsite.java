package com.wxcrm.DTO;
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
public class WcWebsite {

 private  long serialVersionUID;

 private  Integer wcsId;

 private  String wcsWebSiteName;

 private  String wcsAppName;

 private  String wcsAppId;

 private  String wcsAppSecret;

 private  String wcsRedirectUrl;

 private  String wcsToken;

 private  Integer wcsAdminId;

 private  Integer wcsRegistor;

 private  String wcsRegistdate;

 private  String wcsType;

 private  String wcsRandomNum;

 private  String currentPage;

 private  String pageSize;

 private  String[] wcsIds;

// Constructors
/**
 * default constructor
 */
public WcWebsite() {
}/**
 * minimal constructor
 */
public WcWebsite(String wcsWebSiteName) {
    this.wcsWebSiteName = wcsWebSiteName;
}/**
 * full constructor
 */
public WcWebsite(String wcsWebSiteName, String wcsAppName, String wcsAppId, String wcsAppSecret, String wcsRedirectUrl, String wcsToken, Integer wcsAdminId, Integer wcsRegistor, String wcsRegistdate) {
    this.wcsWebSiteName = wcsWebSiteName;
    this.wcsAppName = wcsAppName;
    this.wcsAppId = wcsAppId;
    this.wcsAppSecret = wcsAppSecret;
    this.wcsRedirectUrl = wcsRedirectUrl;
    this.wcsToken = wcsToken;
    this.wcsAdminId = wcsAdminId;
    this.wcsRegistor = wcsRegistor;
    this.wcsRegistdate = wcsRegistdate;
}
@Column(name = "WCS_REDIRECT_URL", length = 200)
public String getWcsRedirectUrl(){
    return this.wcsRedirectUrl;
}


@Column(name = "WCS_RANDOM_NUM", length = 20)
public String getWcsRandomNum(){
    return wcsRandomNum;
}


@Column(name = "WCS_WEbSITE_NAME", nullable = false, length = 200)
public String getWcsWebSiteName(){
    return this.wcsWebSiteName;
}


@Column(name = "WCS_ADMIN_ID")
public Integer getWcsAdminId(){
    return this.wcsAdminId;
}


@Transient
public String[] getWcsIds(){
    return wcsIds;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


@Column(name = "WCS_REGISTDATE", length = 19)
public String getWcsRegistdate(){
    return this.wcsRegistdate;
}


@Column(name = "WCS_TOKEN", length = 80)
public String getWcsToken(){
    return this.wcsToken;
}


@Column(name = "WCS_REGISTOR")
public Integer getWcsRegistor(){
    return this.wcsRegistor;
}


@Transient
public String getPageSize(){
    return pageSize;
}


@Column(name = "WCS_APP_SECRET", length = 100)
public String getWcsAppSecret(){
    return this.wcsAppSecret;
}


@Column(name = "WCS_TYPE", length = 1)
public String getWcsType(){
    return wcsType;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "WCS_ID", unique = true, nullable = false)
public Integer getWcsId(){
    return this.wcsId;
}


@Transient
public long getSerialVersionUID(){
    return serialVersionUID;
}


@Column(name = "WCS_APP_NAME", length = 200)
public String getWcsAppName(){
    return this.wcsAppName;
}


@Column(name = "WCS_APP_ID", length = 100)
public String getWcsAppId(){
    return this.wcsAppId;
}


}