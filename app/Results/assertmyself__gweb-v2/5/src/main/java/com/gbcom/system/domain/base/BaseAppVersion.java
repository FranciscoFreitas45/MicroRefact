package com.gbcom.system.domain.base;
 import java.io.File;
import java.io.Serializable;
public class BaseAppVersion implements Serializable{

 private  int hashCode;

 private  Long id;

 private  String vendorCode;

 private  Integer appOsType;

 private  String appVersionName;

 private  Long appVersionCode;

 private  String appFileName;

 private  String appFilePath;

 private  String appFileSize;

 private  String md5;

 private  String appPlistFileUrl;

 private  Long createTimestamp;

 private  String updateContent;

 private  Boolean state;

 private  File attachment;

 private  String attachmentContentType;

 private  String attachmentFileName;

// constructors
public BaseAppVersion() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseAppVersion(java.lang.Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseAppVersion(java.lang.Long id, Integer appOsType, java.lang.String appVersionName, java.lang.Long appVersionCode) {
    this.setId(id);
    this.setAppOsType(appOsType);
    this.setAppVersionName(appVersionName);
    this.setAppVersionCode(appVersionCode);
    initialize();
}
public String getMd5(){
    return this.md5;
}


public void setAppOsType(Integer appOsType){
    this.appOsType = appOsType;
}


public void setAttachmentContentType(String attachmentContentType){
    this.attachmentContentType = attachmentContentType;
}


public java.lang.Long getId(){
    return id;
}


public String getVendorCode(){
    return vendorCode;
}


public void setAppVersionCode(Long appVersionCode){
    this.appVersionCode = appVersionCode;
}


public Long getCreateTimestamp(){
    return this.createTimestamp;
}


public void setAppFileName(String appFileName){
    this.appFileName = appFileName;
}


public void setCreateTimestamp(Long createTimestamp){
    this.createTimestamp = createTimestamp;
}


public String getUpdateContent(){
    return this.updateContent;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public Long getAppVersionCode(){
    return this.appVersionCode;
}


public String getAppFilePath(){
    return this.appFilePath;
}


public void setAttachmentFileName(String attachmentFileName){
    this.attachmentFileName = attachmentFileName;
}


public File getAttachment(){
    return this.attachment;
}


public String getAppFileName(){
    return this.appFileName;
}


public void setAppFileSize(String appFileSize){
    this.appFileSize = appFileSize;
}


public String getAttachmentFileName(){
    return this.attachmentFileName;
}


public void setVendorCode(String vendorCode){
    this.vendorCode = vendorCode;
}


public void setUpdateContent(String updateContent){
    this.updateContent = updateContent;
}


public Integer getAppOsType(){
    return appOsType;
}


public String getAttachmentContentType(){
    return this.attachmentContentType;
}


public Boolean getState(){
    return this.state;
}


public void setAppFilePath(String appFilePath){
    this.appFilePath = appFilePath;
}


public String getAppPlistFileUrl(){
    return this.appPlistFileUrl;
}


public void setAppVersionName(String appVersionName){
    this.appVersionName = appVersionName;
}


public void setAppPlistFileUrl(String appPlistFileUrl){
    this.appPlistFileUrl = appPlistFileUrl;
}


public void setState(Boolean state){
    this.state = state;
}


public void setAttachment(File attachment){
    this.attachment = attachment;
}


public String getAppFileSize(){
    return this.appFileSize;
}


public void initialize(){
}


public String getAppVersionName(){
    return this.appVersionName;
}


public void setMd5(String md5){
    this.md5 = md5;
}


}