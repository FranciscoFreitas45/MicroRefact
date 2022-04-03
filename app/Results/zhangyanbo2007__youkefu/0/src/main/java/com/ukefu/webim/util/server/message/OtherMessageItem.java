package com.ukefu.webim.util.server.message;
 public class OtherMessageItem {

 private  long serialVersionUID;

 private  String msgtype;

 private  String title;

 private  String id;

 private  String content;

 private  String headimg;

 private  String type;

 private  String url;

 private  String ckind;

 private  String ckindname;

 private  String clabel;

 private  String clabelname;

 private  int duration;


public void setDuration(int duration){
    this.duration = duration;
}


public void setClabelname(String clabelname){
    this.clabelname = clabelname;
}


public String getClabel(){
    return clabel;
}


public void setContent(String content){
    this.content = content;
}


public String getCkind(){
    return ckind;
}


public void setCkind(String ckind){
    this.ckind = ckind;
}


public String getContent(){
    return content;
}


public void setHeadimg(String headimg){
    this.headimg = headimg;
}


public void setClabel(String clabel){
    this.clabel = clabel;
}


public void setTitle(String title){
    this.title = title;
}


public String getId(){
    return id != null ? id : "";
}


public String getClabelname(){
    return clabelname;
}


public void setType(String type){
    this.type = type;
}


public void setCkindname(String ckindname){
    this.ckindname = ckindname;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public String getTitle(){
    return title;
}


public int getDuration(){
    return duration;
}


public String getType(){
    return type;
}


public String getMsgtype(){
    return msgtype;
}


public String getCkindname(){
    return ckindname;
}


public void setId(String id){
    this.id = id;
}


public String getHeadimg(){
    return headimg;
}


public void setMsgtype(String msgtype){
    this.msgtype = msgtype;
}


}