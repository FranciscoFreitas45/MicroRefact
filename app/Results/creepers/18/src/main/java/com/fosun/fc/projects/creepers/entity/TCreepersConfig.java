package com.fosun.fc.projects.creepers.entity;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_CONFIG")
@NamedQuery(name = "TCreepersConfig.findAll", query = "SELECT t FROM TCreepersConfig t")
public class TCreepersConfig implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_CONFIG_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_CONFIG")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_CONFIG_ID_GENERATOR")
 private  long id;

@Column(name = "AGENT_FLAG")
 private  String agentFlag;

@Column(name = "CD_REQUEST_CODE")
 private  String cdRequestCode;

@Column(name = "CD_URL")
 private  String cdUrl;

@Column(name = "DOMAIN")
 private  String domain;

 private  String headers;

 private  String memo;

@Column(name = "REQUEST_TYPE")
 private  String requestType;

@Column(name = "RETRY_TIMES")
 private  String retryTimes;

@Column(name = "ROOT_URL")
 private  String rootUrl;

@Column(name = "SWITCH_FLAG")
 private  String switchFlag;

@Column(name = "THREAD_NUM")
 private  String threadNum;

@Column(name = "TIME_OUT")
 private  String timeOut;

@Column(name = "USER_AGENT")
 private  String userAgent;

public TCreepersConfig() {
}
public void setCdUrl(String cdUrl){
    this.cdUrl = cdUrl;
}


public void setDomain(String domain){
    this.domain = domain;
}


public long getId(){
    return this.id;
}


public void setCdRequestCode(String cdRequestCode){
    this.cdRequestCode = cdRequestCode;
}


public String getRequestType(){
    return this.requestType;
}


public void setTimeOut(String timeOut){
    this.timeOut = timeOut;
}


public String getAgentFlag(){
    return this.agentFlag;
}


public void setId(long id){
    this.id = id;
}


public void setAgentFlag(String agentFlag){
    this.agentFlag = agentFlag;
}


public String getCdRequestCode(){
    return this.cdRequestCode;
}


public void setHeaders(String headers){
    this.headers = headers;
}


public String getHeaders(){
    return this.headers;
}


public String getCdUrl(){
    return this.cdUrl;
}


public void setSwitchFlag(String switchFlag){
    this.switchFlag = switchFlag;
}


public String getThreadNum(){
    return this.threadNum;
}


public void setUserAgent(String userAgent){
    this.userAgent = userAgent;
}


public void setRootUrl(String rootUrl){
    this.rootUrl = rootUrl;
}


public void setRequestType(String requestType){
    this.requestType = requestType;
}


public String getDomain(){
    return this.domain;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getSwitchFlag(){
    return this.switchFlag;
}


public String getMemo(){
    return this.memo;
}


public void setRetryTimes(String retryTimes){
    this.retryTimes = retryTimes;
}


public String getTimeOut(){
    return this.timeOut;
}


public String getRootUrl(){
    return this.rootUrl;
}


public void setThreadNum(String threadNum){
    this.threadNum = threadNum;
}


public String getUserAgent(){
    return this.userAgent;
}


public String getRetryTimes(){
    return this.retryTimes;
}


}