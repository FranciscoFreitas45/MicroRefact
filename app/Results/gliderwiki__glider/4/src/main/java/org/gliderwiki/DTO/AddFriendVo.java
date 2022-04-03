package org.gliderwiki.DTO;
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


public void setWeUserNick(String weUserNick){
    this.weUserNick = weUserNick;
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


public String getWeUserNick(){
    return weUserNick;
}


public void setWeUseYn(String weUseYn){
    this.weUseYn = weUseYn;
}


}