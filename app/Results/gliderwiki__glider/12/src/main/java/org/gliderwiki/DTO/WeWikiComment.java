package org.gliderwiki.DTO;
 import java.io.Serializable;
import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiComment extends BaseObjectBeanimplements Serializable{

 private  long serialVersionUID;

 private  Integer we_wiki_comment_idx;

 private  Integer we_wiki_idx;

 private  String we_user_ip;

 private  String we_bbs_text;

 private  Integer we_ins_user;

 private  Date we_ins_date;

 private  String we_ins_name;

 private  String we_use_yn;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_wiki_comment_idx(){
    return we_wiki_comment_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public String getWe_user_ip(){
    return we_user_ip;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_ins_name(){
    return we_ins_name;
}


public String getWe_bbs_text(){
    return we_bbs_text;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_use_yn"))

.queryParam("we_use_yn",we_use_yn)
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


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_ins_date"))

.queryParam("we_ins_date",we_ins_date)
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


public void setWe_bbs_text(String we_bbs_text){
    this.we_bbs_text = we_bbs_text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_bbs_text"))

.queryParam("we_bbs_text",we_bbs_text)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_user_ip(String we_user_ip){
    this.we_user_ip = we_user_ip;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_ip"))

.queryParam("we_user_ip",we_user_ip)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_ins_name(String we_ins_name){
    this.we_ins_name = we_ins_name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_ins_name"))

.queryParam("we_ins_name",we_ins_name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_wiki_comment_idx(Integer we_wiki_comment_idx){
    this.we_wiki_comment_idx = we_wiki_comment_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_comment_idx"))

.queryParam("we_wiki_comment_idx",we_wiki_comment_idx)
;
restTemplate.put(builder.toUriString(),null);
}


}