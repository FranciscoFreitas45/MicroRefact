package org.gliderwiki.DTO;
 import java.io.Serializable;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
public class WeAlarmInfo implements Serializable{

 private  long serialVersionUID;

 private  Integer we_alarm_idx;

 private  Integer we_meta_idx;

 private  String we_alarm_type;

 private  Integer we_user_idx;

 private  Integer we_target_user_idx;

 private  String we_alarm_text;

 private  String we_use_yn;

 private  String we_ins_date;

 private  Integer we_ins_user;

 private  boolean we_read_yn;

 private  Integer we_space_idx;

 private  Integer we_wiki_idx;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_space_idx(){
    return we_space_idx;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public String getWe_alarm_text(){
    return we_alarm_text;
}


public String getWe_ins_date(){
    return we_ins_date;
}


public Integer getWe_meta_idx(){
    return we_meta_idx;
}


public Integer getWe_alarm_idx(){
    return we_alarm_idx;
}


public Integer getWe_target_user_idx(){
    return we_target_user_idx;
}


public String getWe_alarm_type(){
    return we_alarm_type;
}


public void setWe_meta_idx(Integer we_meta_idx){
    this.we_meta_idx = we_meta_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_meta_idx"))

.queryParam("we_meta_idx",we_meta_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_alarm_type(String we_alarm_type){
    this.we_alarm_type = we_alarm_type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_alarm_type"))

.queryParam("we_alarm_type",we_alarm_type)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_idx"))

.queryParam("we_user_idx",we_user_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_target_user_idx(Integer we_target_user_idx){
    this.we_target_user_idx = we_target_user_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_target_user_idx"))

.queryParam("we_target_user_idx",we_target_user_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_alarm_text(String we_alarm_text){
    this.we_alarm_text = we_alarm_text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_alarm_text"))

.queryParam("we_alarm_text",we_alarm_text)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_use_yn"))

.queryParam("we_use_yn",we_use_yn)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_ins_date(String we_ins_date){
    this.we_ins_date = we_ins_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_ins_date"))

.queryParam("we_ins_date",we_ins_date)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_ins_user(Integer we_ins_user){
    this.we_ins_user = we_ins_user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_ins_user"))

.queryParam("we_ins_user",we_ins_user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_space_idx(Integer we_space_idx){
    this.we_space_idx = we_space_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_space_idx"))

.queryParam("we_space_idx",we_space_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_idx"))

.queryParam("we_wiki_idx",we_wiki_idx)
;
restTemplate.put(builder.toUriString(),null);
}


}