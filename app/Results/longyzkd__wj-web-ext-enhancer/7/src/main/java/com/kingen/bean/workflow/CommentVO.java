package com.kingen.bean.workflow;
 import java.io.Serializable;
import java.util.Date;
public class CommentVO implements Serializable{

 private  long serialVersionUID;

 private  String userName;

 private  String content;

 private  Date time;

public CommentVO() {
}
public void setContent(String content){
    this.content = content;
}


public void setUserName(String userName){
    this.userName = userName;
}


public Date getTime(){
    return time;
}


public String getContent(){
    return content;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public String getUserName(){
    return userName;
}


public void setTime(Date time){
    this.time = time;
}


}