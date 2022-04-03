package org.gliderwiki.web.domain;
 import java.io.Serializable;
import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
@Table("WE_BBS")
public class WeBbs implements Serializable{

 private  long serialVersionUID;

@Column(name = "we_bbs_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_bbs_idx;

@Column(name = "we_space_idx")
 private  Integer we_space_idx;

@Column(name = "we_user_ip")
 private  String we_user_ip;

@Column(name = "we_bbs_title")
 private  String we_bbs_title;

@Column(name = "we_bbs_text")
 private  String we_bbs_text;

@Column(name = "we_use_yn")
 private  String we_use_yn;

@Column(name = "we_ins_user")
 private  Integer we_ins_user;

@Column(name = "we_ins_name")
 private  String we_ins_name;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

@Column(name = "we_upd_user")
 private  Integer we_upd_user;

@Column(name = "we_upd_date")
 private  Date we_upd_date;

@Column(name = "we_hit_count")
 private  Integer we_hit_count;

 private  Integer row_num;

public WeBbs() {
}public WeBbs(Integer we_space_idx) {
    this.we_space_idx = we_space_idx;
    this.we_use_yn = "Y";
}public WeBbs(Integer we_space_idx, String we_user_ip, String we_bbs_title, String we_bbs_text, Integer we_ins_user, String we_ins_name) {
    this.we_space_idx = we_space_idx;
    this.we_user_ip = we_user_ip;
    this.we_bbs_title = we_bbs_title;
    this.we_bbs_text = we_bbs_text;
    this.we_ins_user = we_ins_user;
    this.we_ins_name = we_ins_name;
    this.we_use_yn = "Y";
    this.we_ins_date = new Date();
    this.we_hit_count = 0;
}
public Integer getWe_upd_user(){
    return we_upd_user;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
}


public String getWe_user_ip(){
    return we_user_ip;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public void setWe_bbs_title(String we_bbs_title){
    this.we_bbs_title = we_bbs_title;
}


public void setWe_upd_user(Integer we_upd_user){
    this.we_upd_user = we_upd_user;
}


public String getWe_bbs_title(){
    return we_bbs_title;
}


public void setWe_ins_name(String we_ins_name){
    this.we_ins_name = we_ins_name;
}


public String getWe_bbs_text(){
    return we_bbs_text;
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


public Integer getWe_space_idx(){
    return we_space_idx;
}


public Integer getWe_bbs_idx(){
    return we_bbs_idx;
}


public void setWe_bbs_text(String we_bbs_text){
    this.we_bbs_text = we_bbs_text;
}


public Integer getWe_hit_count(){
    return we_hit_count;
}


public void setWe_ins_user(Integer we_ins_user){
    this.we_ins_user = we_ins_user;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public String getWe_ins_name(){
    return we_ins_name;
}


public void setWe_bbs_idx(Integer we_bbs_idx){
    this.we_bbs_idx = we_bbs_idx;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setRow_num(Integer row_num){
    this.row_num = row_num;
}


public void setWe_hit_count(Integer we_hit_count){
    this.we_hit_count = we_hit_count;
}


public void setWe_user_ip(String we_user_ip){
    this.we_user_ip = we_user_ip;
}


}