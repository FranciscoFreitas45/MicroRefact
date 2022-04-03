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


public void setWe_wiki_markup(String we_wiki_markup){
    this.we_wiki_markup = we_wiki_markup;
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


public void setWe_wiki_status(String we_wiki_status){
    this.we_wiki_status = we_wiki_status;
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


public void setWe_space_idx(Integer we_space_idx){
    this.we_space_idx = we_space_idx;
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


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_wiki_order_idx(Integer we_wiki_order_idx){
    this.we_wiki_order_idx = we_wiki_order_idx;
}


public void setWe_wiki_parent_idx(Integer we_wiki_parent_idx){
    this.we_wiki_parent_idx = we_wiki_parent_idx;
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


public void setWe_wiki_title(String we_wiki_title){
    this.we_wiki_title = we_wiki_title;
}


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public void setWe_wiki_view_cnt(Integer we_wiki_view_cnt){
    this.we_wiki_view_cnt = we_wiki_view_cnt;
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


public void setWe_user_ip(String we_user_ip){
    this.we_user_ip = we_user_ip;
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


public void setWe_edit_text(String we_edit_text){
    this.we_edit_text = we_edit_text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_edit_text"))

.queryParam("we_edit_text",we_edit_text)
;
restTemplate.put(builder.toUriString(),null);
}


public void newBasicSetting(Integer usrIdx,String saveType,String usrIp){
    setWe_use_yn("Y");
    setWe_ins_user(usrIdx);
    setWe_wiki_revision(1);
    // 저장상태
    setWe_wiki_status(saveType);
    setWe_user_ip(usrIp);
    setWe_wiki_agree(0);
    setWe_wiki_view_cnt(0);
    setWe_wiki_protect("0");
    // 정렬순서
    setWe_wiki_order_idx(0);
    // 글 깊이
    setWe_wiki_depth_idx(0);
    // 수정 가능 상태
    setWe_edit_yn("Y");
    setWe_ins_date(new Date());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newBasicSetting"))

.queryParam("usrIdx",usrIdx)
.queryParam("saveType",saveType)
.queryParam("usrIp",usrIp)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_wiki_text(String we_wiki_text){
    this.we_wiki_text = we_wiki_text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_text"))

.queryParam("we_wiki_text",we_wiki_text)
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


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_revision"))

.queryParam("we_wiki_revision",we_wiki_revision)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_wiki_agree(Integer we_wiki_agree){
    this.we_wiki_agree = we_wiki_agree;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_agree"))

.queryParam("we_wiki_agree",we_wiki_agree)
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


public void setWe_edit_yn(String we_edit_yn){
    this.we_edit_yn = we_edit_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_edit_yn"))

.queryParam("we_edit_yn",we_edit_yn)
;
restTemplate.put(builder.toUriString(),null);
}


}