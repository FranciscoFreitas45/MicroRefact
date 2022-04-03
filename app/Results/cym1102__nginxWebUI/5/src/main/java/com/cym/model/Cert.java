package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.SingleIndex;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("证书")
@Table
public class Cert extends BaseModel{

@ApiModelProperty("域名")
@SingleIndex(unique = true)
 private String domain;

@ApiModelProperty("pem文件路径")
 private String pem;

@ApiModelProperty("key文件路径")
 private String key;

@ApiModelProperty("获取方式 0 申请证书 1 手动上传 2 DNS验证")
@InitValue("0")
 private Integer type;

@ApiModelProperty("签发时间戳")
 private Long makeTime;

@ApiModelProperty("是否自动续签 0否 1是")
@InitValue("0")
 private Integer autoRenew;

@ApiModelProperty("dns提供商 ali:阿里云  dp:腾讯云  cf:Cloudflare  gd:Godaddy")
 private String dnsType;

@ApiModelProperty("dpId(腾讯云需要的参数)")
 private String dpId;

@ApiModelProperty("dpKey(腾讯云需要的参数)")
 private String dpKey;

@ApiModelProperty("aliKey(阿里云需要的参数)")
 private String aliKey;

@ApiModelProperty("aliSecret(阿里云需要的参数)")
 private String aliSecret;

@ApiModelProperty("cfEmail(Cloudflare需要的参数)")
 private String cfEmail;

@ApiModelProperty("cfKey(Cloudflare需要的参数)")
 private String cfKey;

@ApiModelProperty("gdKey(Godaddy需要的参数)")
 private String gdKey;

@ApiModelProperty("gdSecret(Godaddy需要的参数)")
 private String gdSecret;

@ApiModelProperty("hwUsername(华为云需要的参数)")
 private String hwUsername;

@ApiModelProperty("hwPassword(华为云需要的参数)")
 private String hwPassword;

@ApiModelProperty("hwProjectID(华为云需要的参数)")
 private String hwProjectID;


public void setHwProjectID(String hwProjectID){
    this.hwProjectID = hwProjectID;
}


public void setDpId(String dpId){
    this.dpId = dpId;
}


public String getDpId(){
    return dpId;
}


public void setGdKey(String gdKey){
    this.gdKey = gdKey;
}


public String getCfKey(){
    return cfKey;
}


public void setDomain(String domain){
    this.domain = domain;
}


public void setHwUsername(String hwUsername){
    this.hwUsername = hwUsername;
}


public String getHwPassword(){
    return hwPassword;
}


public String getAliSecret(){
    return aliSecret;
}


public void setAliSecret(String aliSecret){
    this.aliSecret = aliSecret;
}


public String getHwUsername(){
    return hwUsername;
}


public String getHwProjectID(){
    return hwProjectID;
}


public void setDpKey(String dpKey){
    this.dpKey = dpKey;
}


public void setGdSecret(String gdSecret){
    this.gdSecret = gdSecret;
}


public void setAutoRenew(Integer autoRenew){
    this.autoRenew = autoRenew;
}


public void setPem(String pem){
    this.pem = pem;
}


public Long getMakeTime(){
    return makeTime;
}


public void setAliKey(String aliKey){
    this.aliKey = aliKey;
}


public String getAliKey(){
    return aliKey;
}


public Integer getAutoRenew(){
    return autoRenew;
}


public void setDnsType(String dnsType){
    this.dnsType = dnsType;
}


public void setKey(String key){
    this.key = key;
}


public String getCfEmail(){
    return cfEmail;
}


public String getGdSecret(){
    return gdSecret;
}


public void setMakeTime(Long makeTime){
    this.makeTime = makeTime;
}


public String getKey(){
    return key;
}


public void setHwPassword(String hwPassword){
    this.hwPassword = hwPassword;
}


public void setCfEmail(String cfEmail){
    this.cfEmail = cfEmail;
}


public String getDnsType(){
    return dnsType;
}


public String getPem(){
    return pem;
}


public void setType(Integer type){
    this.type = type;
}


public String getDpKey(){
    return dpKey;
}


public String getDomain(){
    return domain;
}


public void setCfKey(String cfKey){
    this.cfKey = cfKey;
}


public Integer getType(){
    return type;
}


public String getGdKey(){
    return gdKey;
}


}