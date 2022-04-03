package org.gliderwiki.web.domain;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_WIKI")
public class WeWiki extends BaseObjectBean{

 private  long serialVersionUID;

@Column(name = "we_wiki_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_wiki_idx;

@Column(name = "we_space_idx")
 private  Integer we_space_idx;

@Column(name = "we_wiki_parent_idx")
 private  Integer we_wiki_parent_idx;

@Column(name = "we_wiki_order_idx")
 private  Integer we_wiki_order_idx;

@Column(name = "we_wiki_depth_idx")
 private  Integer we_wiki_depth_idx;

@Column(name = "we_wiki_title")
 private  String we_wiki_title;

@Column(name = "we_wiki_text")
 private  String we_wiki_text;

@Column(name = "we_wiki_markup")
 private  String we_wiki_markup;

@Column(name = "we_wiki_revision")
 private  Integer we_wiki_revision;

@Column(name = "we_wiki_status")
 private  String we_wiki_status;

@Column(name = "we_wiki_quota")
 private  Integer we_wiki_quota;

@Column(name = "we_wiki_url")
 private  String we_wiki_url;

@Column(name = "we_wiki_agree")
 private  Integer we_wiki_agree;

@Column(name = "we_wiki_view_cnt")
 private  Integer we_wiki_view_cnt;

@Column(name = "we_wiki_prev")
 private  String we_wiki_prev;

@Column(name = "we_wiki_next")
 private  String we_wiki_next;

@Column(name = "we_user_ip")
 private  String we_user_ip;

@Column(name = "we_wiki_protect")
 private  String we_wiki_protect;

@Column(name = "we_edit_text")
 private  String we_edit_text;

@Column(name = "we_use_yn")
 private  String we_use_yn;

@Column(name = "we_edit_yn")
 private  String we_edit_yn;

@Column(name = "we_ins_user")
 private  Integer we_ins_user;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

@Column(name = "we_upd_user")
 private  Integer we_upd_user;

@Column(name = "we_upd_date")
 private  Date we_upd_date;

 private  String we_text_br;

 private  Integer row_num;

 private  String we_user_nick;

public WeWiki() {
}
public Integer getWe_upd_user(){
    return we_upd_user;
}


public void setWe_edit_text(String we_edit_text){
    this.we_edit_text = we_edit_text;
}


public String getWe_edit_yn(){
    return we_edit_yn;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
}


public void setWe_wiki_markup(String we_wiki_markup){
    this.we_wiki_markup = we_wiki_markup;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
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


public void setWe_upd_user(Integer we_upd_user){
    this.we_upd_user = we_upd_user;
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


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
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


public void setWe_text_br(String we_text_br){
    this.we_text_br = we_text_br;
}


public String getWe_user_nick(){
    return we_user_nick;
}


public void setWe_wiki_quota(Integer we_wiki_quota){
    this.we_wiki_quota = we_wiki_quota;
}


public Integer getWe_wiki_parent_idx(){
    return we_wiki_parent_idx;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public void setWe_user_nick(String we_user_nick){
    this.we_user_nick = we_user_nick;
}


public Integer getWe_wiki_order_idx(){
    return we_wiki_order_idx;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public String getWe_wiki_markup(){
    return we_wiki_markup;
}


public void setRow_num(Integer row_num){
    this.row_num = row_num;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_wiki_next(String we_wiki_next){
    this.we_wiki_next = we_wiki_next;
}


public void setWe_wiki_order_idx(Integer we_wiki_order_idx){
    this.we_wiki_order_idx = we_wiki_order_idx;
}


public void setWe_wiki_depth_idx(Integer we_wiki_depth_idx){
    this.we_wiki_depth_idx = we_wiki_depth_idx;
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


public void setWe_wiki_agree(Integer we_wiki_agree){
    this.we_wiki_agree = we_wiki_agree;
}


public void setWe_wiki_title(String we_wiki_title){
    this.we_wiki_title = we_wiki_title;
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
}


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public void setWe_wiki_url(String we_wiki_url){
    this.we_wiki_url = we_wiki_url;
}


public void setWe_wiki_view_cnt(Integer we_wiki_view_cnt){
    this.we_wiki_view_cnt = we_wiki_view_cnt;
}


public Integer getWe_space_idx(){
    return we_space_idx;
}


public void setWe_wiki_text(String we_wiki_text){
    this.we_wiki_text = we_wiki_text;
}


public String getWe_wiki_next(){
    return we_wiki_next;
}


public void setWe_ins_user(Integer we_ins_user){
    this.we_ins_user = we_ins_user;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public void setWe_wiki_protect(String we_wiki_protect){
    this.we_wiki_protect = we_wiki_protect;
}


public Integer getWe_wiki_view_cnt(){
    return we_wiki_view_cnt;
}


public void setWe_edit_yn(String we_edit_yn){
    this.we_edit_yn = we_edit_yn;
}


public void setWe_user_ip(String we_user_ip){
    this.we_user_ip = we_user_ip;
}


public void setWe_wiki_prev(String we_wiki_prev){
    this.we_wiki_prev = we_wiki_prev;
}


}