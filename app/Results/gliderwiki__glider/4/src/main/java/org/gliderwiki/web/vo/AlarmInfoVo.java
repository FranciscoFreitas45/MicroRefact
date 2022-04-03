package org.gliderwiki.web.vo;
 import java.util.Date;
public class AlarmInfoVo {

 private  Integer weAlarmIdx;

 private  Integer weMetaIdx;

 private  String weAlarmType;

 private  String weAlarmDomain;

 private  Integer weUserIdx;

 private  Integer weTargetUserIdx;

 private  String weAlarmText;

 private  String weUseYn;

 private  Date weInsDate;

 private  String weInsUser;


public Integer getWeAlarmIdx(){
    return weAlarmIdx;
}


public Integer getWeMetaIdx(){
    return weMetaIdx;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public void setWeAlarmIdx(Integer weAlarmIdx){
    this.weAlarmIdx = weAlarmIdx;
}


public void setWeInsUser(String weInsUser){
    this.weInsUser = weInsUser;
}


public void setWeAlarmType(String weAlarmType){
    this.weAlarmType = weAlarmType;
}


public String getWeAlarmType(){
    return weAlarmType;
}


public void setWeUseYn(String weUseYn){
    this.weUseYn = weUseYn;
}


public String getWeAlarmDomain(){
    return weAlarmDomain;
}


public void setWeAlarmDomain(String weAlarmDomain){
    this.weAlarmDomain = weAlarmDomain;
}


public Integer getWeTargetUserIdx(){
    return weTargetUserIdx;
}


public String getWeAlarmText(){
    return weAlarmText;
}


public void setWeInsDate(Date weInsDate){
    this.weInsDate = weInsDate;
}


public void setWeTargetUserIdx(Integer weTargetUserIdx){
    this.weTargetUserIdx = weTargetUserIdx;
}


public String getWeUseYn(){
    return weUseYn;
}


public void setWeMetaIdx(Integer weMetaIdx){
    this.weMetaIdx = weMetaIdx;
}


public void setWeUserIdx(Integer weUserIdx){
    this.weUserIdx = weUserIdx;
}


public String getWeInsUser(){
    return weInsUser;
}


public Date getWeInsDate(){
    return weInsDate;
}


public void setWeAlarmText(String weAlarmText){
    this.weAlarmText = weAlarmText;
}


}