package org.gliderwiki.web.vo;
 public class GroupUserVo extends BaseObjectBean{

 private  Integer weUserIdx;

 private  String weUserId;

 private  String weUserNick;

 private  String weInsDate;

 private  Integer weGroupIdx;

 private  String weUseYn;

 private  String weUserName;


public String getWeUserId(){
    return weUserId;
}


public String getWeUserName(){
    return weUserName;
}


public void setWeUserNick(String weUserNick){
    this.weUserNick = weUserNick;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public String getWeUserNick(){
    return weUserNick;
}


public void setWeUseYn(String weUseYn){
    this.weUseYn = weUseYn;
}


public Integer getWeGroupIdx(){
    return weGroupIdx;
}


public void setWeInsDate(String weInsDate){
    this.weInsDate = weInsDate;
}


public void setWeUserId(String weUserId){
    this.weUserId = weUserId;
}


public String getWeUseYn(){
    return weUseYn;
}


public void setWeUserIdx(Integer weUserIdx){
    this.weUserIdx = weUserIdx;
}


public void setWeUserName(String weUserName){
    this.weUserName = weUserName;
}


public String getWeInsDate(){
    return weInsDate;
}


public void setWeGroupIdx(Integer weGroupIdx){
    this.weGroupIdx = weGroupIdx;
}


}