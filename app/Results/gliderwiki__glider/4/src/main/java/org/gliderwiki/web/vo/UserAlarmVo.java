package org.gliderwiki.web.vo;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
public class UserAlarmVo {

 private  Integer weUserIdx;

 private  Integer weMetaIdx;

 private  String weUseYn;

 private  String weInsDate;

 private  String weInsUser;

 private  String weUpdDate;

 private  String weUpdUser;


public void setWeUpdUser(String weUpdUser){
    this.weUpdUser = weUpdUser;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public Integer getWeMetaIdx(){
    return weMetaIdx;
}


public void setWeInsUser(String weInsUser){
    this.weInsUser = weInsUser;
}


public void setWeUseYn(String weUseYn){
    this.weUseYn = weUseYn;
}


public void setWeInsDate(String weInsDate){
    this.weInsDate = weInsDate;
}


public String getWeUpdDate(){
    return weUpdDate;
}


public String getWeUseYn(){
    return weUseYn;
}


public void setWeUserIdx(Integer weUserIdx){
    this.weUserIdx = weUserIdx;
}


public void setWeMetaIdx(Integer weMetaIdx){
    this.weMetaIdx = weMetaIdx;
}


public String getWeInsUser(){
    return weInsUser;
}


public String getWeInsDate(){
    return weInsDate;
}


public String getWeUpdUser(){
    return weUpdUser;
}


public void setWeUpdDate(String weUpdDate){
    this.weUpdDate = weUpdDate;
}


}