package org.gliderwiki.web.vo;
 public class AddFriendVo extends BaseObjectBean{

 private  Integer weUserIdx;

 private  Integer weTargetUserIdx;

 private  String weUserNick;

 private  String weAddDate;

 private  String weUseYn;

 private  String weDelDate;


public Integer getWeTargetUserIdx(){
    return weTargetUserIdx;
}


public String getWeDelDate(){
    return weDelDate;
}


public void setWeDelDate(String weDelDate){
    this.weDelDate = weDelDate;
}


public void setWeUserNick(String weUserNick){
    this.weUserNick = weUserNick;
}


public void setWeTargetUserIdx(Integer weTargetUserIdx){
    this.weTargetUserIdx = weTargetUserIdx;
}


public String getWeAddDate(){
    return weAddDate;
}


public String getWeUseYn(){
    return weUseYn;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public void setWeUserIdx(Integer weUserIdx){
    this.weUserIdx = weUserIdx;
}


public String getWeUserNick(){
    return weUserNick;
}


public void setWeAddDate(String weAddDate){
    this.weAddDate = weAddDate;
}


public void setWeUseYn(String weUseYn){
    this.weUseYn = weUseYn;
}


}