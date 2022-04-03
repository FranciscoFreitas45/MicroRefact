package org.gliderwiki.web.vo;
 public class GroupInfoVo extends BaseObjectBean{

 private  Integer weGroupIdx;

 private  String weGroupName;

 private  String weGroupType;

 private  String weGroupOwner;

 private  String weGroupInfo;

 private  String weInsDate;

 private  String weUserNick;


public void setWeUserNick(String weUserNick){
    this.weUserNick = weUserNick;
}


public void setWeGroupOwner(String weGroupOwner){
    this.weGroupOwner = weGroupOwner;
}


public String getWeUserNick(){
    return weUserNick;
}


public Integer getWeGroupIdx(){
    return weGroupIdx;
}


public String getWeGroupInfo(){
    return weGroupInfo;
}


public String getWeGroupName(){
    return weGroupName;
}


public void setWeGroupInfo(String weGroupInfo){
    this.weGroupInfo = weGroupInfo;
}


public void setWeInsDate(String weInsDate){
    this.weInsDate = weInsDate;
}


public void setWeGroupName(String weGroupName){
    this.weGroupName = weGroupName;
}


public String getWeGroupType(){
    return weGroupType;
}


public void setWeGroupType(String weGroupType){
    this.weGroupType = weGroupType;
}


public String getWeGroupOwner(){
    return weGroupOwner;
}


public String getWeInsDate(){
    return weInsDate;
}


public void setWeGroupIdx(Integer weGroupIdx){
    this.weGroupIdx = weGroupIdx;
}


}