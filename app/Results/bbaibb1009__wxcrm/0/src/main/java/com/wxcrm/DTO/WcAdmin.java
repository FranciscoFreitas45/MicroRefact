package com.wxcrm.DTO;
 import javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

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


@Column(name = "WAD_PWD", length = 80)
public String getWadPwd(){
    return this.wadPwd;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


@Column(name = "WAD_REGISTDATE", length = 19)
public String getWadRegistdate(){
    return this.wadRegistdate;
}


@Column(name = "WAD_USERNAME", length = 20)
public String getWadUsername(){
    return this.wadUsername;
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


public void setWadPwdMd5(String wadPwdMd5){
    this.wadPwdMd5 = wadPwdMd5;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWadPwdMd5"))

.queryParam("wadPwdMd5",wadPwdMd5)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWadUsername(String wadUsername){
    this.wadUsername = wadUsername;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWadUsername"))

.queryParam("wadUsername",wadUsername)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWadPwd(String wadPwd){
    this.wadPwd = wadPwd;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWadPwd"))

.queryParam("wadPwd",wadPwd)
;
restTemplate.put(builder.toUriString(),null);
}


@Transient
public boolean isRemember(){
    return remember;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isRemember"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}