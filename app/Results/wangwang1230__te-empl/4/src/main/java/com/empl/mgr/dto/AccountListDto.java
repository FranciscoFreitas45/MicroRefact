package com.empl.mgr.dto;
 import java.io.Serializable;
import java.util.Date;
public class AccountListDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

 private  String nickname;

 private  Date date;

 private  String creator;

 private  String time;

 private  boolean initAccount;

public AccountListDto() {
// TODO Auto-generated constructor stub
}public AccountListDto(long acctId, String acctName, String acctNickname, Date createTime, String creator, boolean acctSuper) {
    super();
    this.id = acctId;
    this.name = acctName;
    this.nickname = acctNickname;
    this.date = createTime;
    this.creator = creator;
    this.initAccount = acctSuper;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public boolean isInitAccount(){
    return initAccount;
}


public String getTime(){
    return time;
}


public void setInitAccount(boolean initAccount){
    this.initAccount = initAccount;
}


public void setCreator(String creator){
    this.creator = creator;
}


public long getId(){
    return id;
}


public String getCreator(){
    return creator;
}


public String getNickname(){
    return nickname;
}


public void setId(long id){
    this.id = id;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public void setDate(Date date){
    this.date = date;
}


@Override
public String toString(){
    return "AccountListDto [id:" + id + ", name:" + name + ", nickname:" + nickname + ", date:" + date + ", creator:" + creator + ", time:" + time + ", initAccount:" + initAccount + "]";
}


public Date getDate(){
    return date;
}


public void setTime(String time){
    this.time = time;
}


}