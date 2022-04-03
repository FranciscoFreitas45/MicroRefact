package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Server extends BaseModel{

 private String serverName;

 private String listen;

 private Integer def;

 private Integer rewrite;

 private String rewriteListen;

 private Integer ssl;

 private Integer http2;

 private Integer proxyProtocol;

 private String pem;

 private String key;

 private Integer proxyType;

 private String proxyUpstreamId;

 private String pemStr;

 private String keyStr;

 private Boolean enable;

 private String descr;

 private String protocols;

 private String passwordId;

 private Long seq;


public void setDef(Integer def){
    this.def = def;
}


public String getDescr(){
    return descr;
}


public Integer getDef(){
    return def;
}


public void setSsl(Integer ssl){
    this.ssl = ssl;
}


public String getPasswordId(){
    return passwordId;
}


public String getProxyUpstreamId(){
    return proxyUpstreamId;
}


public String getServerName(){
    return serverName;
}


public String getProtocols(){
    return protocols;
}


public void setProtocols(String protocols){
    this.protocols = protocols;
}


public String getRewriteListen(){
    return rewriteListen;
}


public Integer getProxyProtocol(){
    return proxyProtocol;
}


public void setPem(String pem){
    this.pem = pem;
}


public Boolean getEnable(){
    return enable;
}


public void setPemStr(String pemStr){
    this.pemStr = pemStr;
}


public String getPemStr(){
    return pemStr;
}


public void setProxyUpstreamId(String proxyUpstreamId){
    this.proxyUpstreamId = proxyUpstreamId;
}


public Integer getProxyType(){
    return proxyType;
}


public void setListen(String listen){
    this.listen = listen;
}


public void setProxyProtocol(Integer proxyProtocol){
    this.proxyProtocol = proxyProtocol;
}


public String getListen(){
    return listen;
}


public Integer getSsl(){
    return ssl;
}


public void setSeq(Long seq){
    this.seq = seq;
}


public void setKey(String key){
    this.key = key;
}


public Long getSeq(){
    return seq;
}


public String getKey(){
    return key;
}


public void setDescr(String descr){
    this.descr = descr;
}


public void setEnable(Boolean enable){
    this.enable = enable;
}


public void setProxyType(Integer proxyType){
    this.proxyType = proxyType;
}


public String getPem(){
    return pem;
}


public void setRewrite(Integer rewrite){
    this.rewrite = rewrite;
}


public void setPasswordId(String passwordId){
    this.passwordId = passwordId;
}


public void setHttp2(Integer http2){
    this.http2 = http2;
}


public void setRewriteListen(String rewriteListen){
    this.rewriteListen = rewriteListen;
}


public Integer getHttp2(){
    return http2;
}


public Integer getRewrite(){
    return rewrite;
}


public void setServerName(String serverName){
    this.serverName = serverName;
}


public String getKeyStr(){
    return keyStr;
}


public void setKeyStr(String keyStr){
    this.keyStr = keyStr;
}


}