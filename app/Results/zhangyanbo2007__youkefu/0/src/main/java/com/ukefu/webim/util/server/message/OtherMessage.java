package com.ukefu.webim.util.server.message;
 import java.util.List;
public class OtherMessage {

 private  long serialVersionUID;

 private  String msgtype;

 private  String title;

 private  String message;

 private  String type;

 private  String id;

 private  boolean trans;

 private  String code;

 private  String score;

 private  boolean detail;

 private  String matchtype;

 private  String ckind;

 private  String ckindname;

 private  String clabel;

 private  String clabelname;

 private  int duration;

 private  List<OtherMessageItem> items;


public void setDuration(int duration){
    this.duration = duration;
}


public String getClabel(){
    return clabel;
}


public String getCkind(){
    return ckind;
}


public String getId(){
    return id;
}


public String getClabelname(){
    return clabelname;
}


public boolean isDetail(){
    return detail;
}


public List<OtherMessageItem> getItems(){
    return items;
}


public String getTitle(){
    return title;
}


public int getDuration(){
    return duration;
}


public void setMatchtype(String matchtype){
    this.matchtype = matchtype;
}


public String getCkindname(){
    return ckindname;
}


public void setItems(List<OtherMessageItem> items){
    this.items = items;
}


public void setId(String id){
    this.id = id;
}


public void setMsgtype(String msgtype){
    this.msgtype = msgtype;
}


public String getMatchtype(){
    return matchtype;
}


public String getCode(){
    return code;
}


public boolean isTrans(){
    return trans;
}


public void setClabelname(String clabelname){
    this.clabelname = clabelname;
}


public void setCkind(String ckind){
    this.ckind = ckind;
}


public void setDetail(boolean detail){
    this.detail = detail;
}


public void setTrans(boolean trans){
    this.trans = trans;
}


public void setCode(String code){
    this.code = code;
}


public void setClabel(String clabel){
    this.clabel = clabel;
}


public void setTitle(String title){
    this.title = title;
}


public String getMessage(){
    return message;
}


public void setMessage(String message){
    this.message = message;
}


public void setType(String type){
    this.type = type;
}


public void setCkindname(String ckindname){
    this.ckindname = ckindname;
}


public String getType(){
    return type;
}


public String getMsgtype(){
    return msgtype;
}


public String getScore(){
    return score;
}


public void setScore(String score){
    this.score = score;
}


}