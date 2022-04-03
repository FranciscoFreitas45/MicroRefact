package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("反向代理server")
@Table
public class Server extends BaseModel{

@ApiModelProperty("监听域名")
 private String serverName;

@ApiModelProperty("*监听ip端口 (格式 ip:port或port)")
 private String listen;

@ApiModelProperty("是否为默认server 0否(默认) 1是")
@InitValue("0")
 private Integer def;

@ApiModelProperty("是否http跳转https 0否(默认) 1是")
@InitValue("0")
 private Integer rewrite;

@ApiModelProperty("http跳转https监听ip端口,默认为80 (格式 ip:port或port)")
@InitValue("80")
 private String rewriteListen;

@ApiModelProperty("是否开启ssl 0否(默认) 1是")
@InitValue("0")
 private Integer ssl;

@ApiModelProperty("是否开启http2 0否(默认) 1是")
@InitValue("0")
 private Integer http2;

@ApiModelProperty("是否开启proxy_protocol 0否(默认) 1是")
@InitValue("0")
 private Integer proxyProtocol;

@ApiModelProperty("ssl证书pem文件路径")
 private String pem;

@ApiModelProperty("ssl证书key文件路径")
 private String key;

@ApiModelProperty("代理类型 0:http(默认) 1:tcp 2:udp")
@InitValue("0")
 private Integer proxyType;

@ApiModelProperty("代理upstream的id")
 private String proxyUpstreamId;

@ApiModelProperty(hidden = true)
 private String pemStr;

@ApiModelProperty(hidden = true)
 private String keyStr;

@ApiModelProperty("是否启用 true:启用(默认) false:禁用")
@InitValue("true")
 private Boolean enable;

@ApiModelProperty("描述")
 private String descr;

@ApiModelProperty("加密协议 (默认:TLSv1 TLSv1.1 TLSv1.2 TLSv1.3)")
@InitValue("TLSv1 TLSv1.1 TLSv1.2 TLSv1.3")
 private String protocols;

@ApiModelProperty("使用的password文件Id")
 private String passwordId;

@ApiModelProperty(hidden = true)
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