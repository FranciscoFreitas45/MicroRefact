package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeUser extends BaseObjectBean{

 private  long serialVersionUID;

 private  Integer we_user_idx;

 private  String we_user_id;

 private  String we_user_name;

 private  String we_user_nick;

 private  String we_user_key;

 private  String we_user_pwd;

 private  Date we_user_join_date;

 private  String we_user_auth_yn;

 private  String we_user_auth;

 private  Date we_user_auth_date;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

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


public String getWe_user_auth_yn(){
    return we_user_auth_yn;
}


public String getWe_user_auth(){
    return we_user_auth;
}


public String getWe_user_id(){
    return we_user_id;
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


public void setWe_user_pwd(String we_user_pwd){
    this.we_user_pwd = we_user_pwd;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_pwd"))

.queryParam("we_user_pwd",we_user_pwd)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_user_name(String we_user_name){
    this.we_user_name = we_user_name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_name"))

.queryParam("we_user_name",we_user_name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_user_nick(String we_user_nick){
    this.we_user_nick = we_user_nick;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_nick"))

.queryParam("we_user_nick",we_user_nick)
;
restTemplate.put(builder.toUriString(),null);
}


}