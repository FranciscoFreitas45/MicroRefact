package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWiki extends BaseObjectBean{

 private  long serialVersionUID;

 private  Integer we_wiki_idx;

 private  Integer we_space_idx;

 private  Integer we_wiki_parent_idx;

 private  Integer we_wiki_order_idx;

 private  Integer we_wiki_depth_idx;

 private  String we_wiki_title;

 private  String we_wiki_text;

 private  String we_wiki_markup;

 private  Integer we_wiki_revision;

 private  String we_wiki_status;

 private  Integer we_wiki_quota;

 private  String we_wiki_url;

 private  Integer we_wiki_agree;

 private  Integer we_wiki_view_cnt;

 private  String we_wiki_prev;

 private  String we_wiki_next;

 private  String we_user_ip;

 private  String we_wiki_protect;

 private  String we_edit_text;

 private  String we_use_yn;

 private  String we_edit_yn;

 private  Integer we_ins_user;

 private  Date we_ins_date;

 private  Integer we_upd_user;

 private  Date we_upd_date;

 private  String we_text_br;

 private  Integer row_num;

 private  String we_user_nick;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public WeWiki() {
}
public Integer getWe_upd_user(){
    return we_upd_user;
}


public String getWe_edit_yn(){
    return we_edit_yn;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public Integer getWe_wiki_quota(){
    return we_wiki_quota;
}


public String getWe_user_ip(){
    return we_user_ip;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_wiki_protect(){
    return we_wiki_protect;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_wiki_status(){
    return we_wiki_status;
}


public Integer getWe_wiki_agree(){
    return we_wiki_agree;
}


public Integer getRow_num(){
    return row_num;
}


public Integer getWe_wiki_depth_idx(){
    return we_wiki_depth_idx;
}


public String getWe_wiki_text(){
    return we_wiki_text;
}


public String getWe_wiki_prev(){
    return we_wiki_prev;
}


public String getWe_user_nick(){
    return we_user_nick;
}


public Integer getWe_wiki_parent_idx(){
    return we_wiki_parent_idx;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public Integer getWe_wiki_order_idx(){
    return we_wiki_order_idx;
}


public String getWe_wiki_markup(){
    return we_wiki_markup;
}


public String getWe_wiki_title(){
    return we_wiki_title;
}


public String getWe_edit_text(){
    return we_edit_text;
}


public String getWe_wiki_url(){
    return we_wiki_url;
}


public String getWe_text_br(){
    return we_text_br;
}


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_space_idx(){
    return we_space_idx;
}


public String getWe_wiki_next(){
    return we_wiki_next;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public Integer getWe_wiki_view_cnt(){
    return we_wiki_view_cnt;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_idx"))

.queryParam("we_wiki_idx",we_wiki_idx)
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


public void setWe_upd_user(Integer we_upd_user){
    this.we_upd_user = we_upd_user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_upd_user"))

.queryParam("we_upd_user",we_upd_user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_wiki_protect(String we_wiki_protect){
    this.we_wiki_protect = we_wiki_protect;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_protect"))

.queryParam("we_wiki_protect",we_wiki_protect)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_upd_date"))

.queryParam("we_upd_date",we_upd_date)
;
restTemplate.put(builder.toUriString(),null);
}


}