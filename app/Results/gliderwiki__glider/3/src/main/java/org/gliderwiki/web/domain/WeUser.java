package org.gliderwiki.web.domain;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_USER")
public class WeUser extends BaseObjectBean{

 private  long serialVersionUID;

@Column(primaryKey = true, autoIncrement = true)
 private  Integer we_user_idx;

@Column(name = "we_user_id")
 private  String we_user_id;

@Column(name = "we_user_name")
 private  String we_user_name;

@Column(name = "we_user_nick")
 private  String we_user_nick;

@Column(name = "we_user_key")
 private  String we_user_key;

@Column(name = "we_user_pwd")
 private  String we_user_pwd;

@Column(name = "we_user_join_date")
 private  Date we_user_join_date;

@Column(name = "we_user_auth_yn")
 private  String we_user_auth_yn;

@Column(name = "we_user_auth")
 private  String we_user_auth;

@Column(name = "we_user_auth_date")
 private  Date we_user_auth_date;

public WeUser() {
}public WeUser(String we_user_id, String we_user_pwd) {
    this.we_user_id = we_user_id;
    this.we_user_pwd = we_user_pwd;
}public WeUser(Integer we_user_idx, String we_user_auth_yn) {
    this.we_user_idx = we_user_idx;
    this.we_user_auth_yn = we_user_auth_yn;
}
public String getWe_user_key(){
    return we_user_key;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public String getWe_user_pwd(){
    return we_user_pwd;
}


public String getWe_user_nick(){
    return we_user_nick;
}


public void setWe_user_nick(String we_user_nick){
    this.we_user_nick = we_user_nick;
}


public void setWe_user_auth_date(Date we_user_auth_date){
    this.we_user_auth_date = we_user_auth_date;
}


public void setWe_user_id(String we_user_id){
    this.we_user_id = we_user_id;
}


public void setWe_user_auth(String we_user_auth){
    this.we_user_auth = we_user_auth;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
}


public void setWe_user_name(String we_user_name){
    this.we_user_name = we_user_name;
}


public void setWe_user_pwd(String we_user_pwd){
    this.we_user_pwd = we_user_pwd;
}


public String getWe_user_auth_yn(){
    return we_user_auth_yn;
}


public void setWe_user_key(String we_user_key){
    this.we_user_key = we_user_key;
}


public String getWe_user_auth(){
    return we_user_auth;
}


public void setWe_user_join_date(Date we_user_join_date){
    this.we_user_join_date = we_user_join_date;
}


public String getWe_user_id(){
    return we_user_id;
}


public void setWe_user_auth_yn(String we_user_auth_yn){
    this.we_user_auth_yn = we_user_auth_yn;
}


public Date getWe_user_join_date(){
    return we_user_join_date;
}


public Date getWe_user_auth_date(){
    return we_user_auth_date;
}


public String getWe_user_name(){
    return we_user_name;
}


}