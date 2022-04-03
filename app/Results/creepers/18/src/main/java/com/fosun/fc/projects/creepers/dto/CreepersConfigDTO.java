package com.fosun.fc.projects.creepers.dto;
 public class CreepersConfigDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String requestType;

 private  String rootUrl;

 private  String domain;

 private  String userAgent;

 private  String cdUrl;

 private  String cdRequestCode;

 private  String threadNum;

 private  String timeOut;

 private  String switchFlag;

 private  String agentFlag;

 private  String retryTimes;

 private  String headers;


public void setCdUrl(String cdUrl){
    this.cdUrl = cdUrl;
}


public void setHeaders(String headers){
    this.headers = headers;
}


public String getHeaders(){
    return headers;
}


public void setDomain(String domain){
    this.domain = domain;
}


public String getCdUrl(){
    return cdUrl;
}


public void setUserAgent(String userAgent){
    this.userAgent = userAgent;
}


public String getThreadNum(){
    return threadNum;
}


public void setSwitchFlag(String switchFlag){
    this.switchFlag = switchFlag;
}


public void setRootUrl(String rootUrl){
    this.rootUrl = rootUrl;
}


public void setCdRequestCode(String cdRequestCode){
    this.cdRequestCode = cdRequestCode;
}


public void setRequestType(String requestType){
    this.requestType = requestType;
}


public String getRequestType(){
    return requestType;
}


public String getDomain(){
    return domain;
}


public String getSwitchFlag(){
    return switchFlag;
}


public void setTimeOut(String timeOut){
    this.timeOut = timeOut;
}


public void setRetryTimes(String retryTimes){
    this.retryTimes = retryTimes;
}


public String getTimeOut(){
    return timeOut;
}


public String getRootUrl(){
    return rootUrl;
}


public String getAgentFlag(){
    return agentFlag;
}


public String getUserAgent(){
    return userAgent;
}


public void setThreadNum(String threadNum){
    this.threadNum = threadNum;
}


public void setAgentFlag(String agentFlag){
    this.agentFlag = agentFlag;
}


public String getCdRequestCode(){
    return cdRequestCode;
}


public String getRetryTimes(){
    return retryTimes;
}


}