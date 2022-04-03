package com.gbcom.common.drools.domain;
 import java.io.Serializable;
import java.util.Date;
import java.util.Set;
public class DrlRule implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String name;

 private  int scope;

 private  String clz;

 private  Set<DrlRuleContext> rules;

 private  String ruleContext;

 private  String sysmodel;

 private  String version;

 private  boolean needReboot;

 private  int state;

 private  Date beginTime;

 private  Date endTime;

 private  int trigerTime;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setSysmodel(String sysmodel){
    this.sysmodel = sysmodel;
}


public Long getId(){
    return id;
}


public boolean isNeedReboot(){
    return needReboot;
}


public Date getEndTime(){
    return endTime;
}


public void setTrigerTime(int trigerTime){
    this.trigerTime = trigerTime;
}


public void setRuleContext(String ruleContext){
    this.ruleContext = ruleContext;
}


public void setId(Long id){
    this.id = id;
}


public void setBeginTime(Date beginTime){
    this.beginTime = beginTime;
}


public Date getBeginTime(){
    return beginTime;
}


public Set<DrlRuleContext> getRules(){
    return rules;
}


public void setRules(Set<DrlRuleContext> rules){
    this.rules = rules;
}


public String getVersion(){
    return version;
}


public void setVersion(String version){
    this.version = version;
}


public int getTrigerTime(){
    return trigerTime;
}


public void setClz(String clz){
    this.clz = clz;
}


public int getState(){
    return state;
}


public String getClz(){
    return clz;
}


public String getSysmodel(){
    return sysmodel;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setNeedReboot(boolean needReboot){
    this.needReboot = needReboot;
}


public void setState(int state){
    this.state = state;
}


@Override
public String toString(){
    return "DrlRule [name=" + name + ", scope=" + scope + ", ruleContext=" + ruleContext + ", version=" + version + "]";
}


public void setScope(int scope){
    this.scope = scope;
}


public int getScope(){
    return scope;
}


public String getRuleContext(){
    return ruleContext;
}


}