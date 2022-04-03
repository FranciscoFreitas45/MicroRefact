package org.gliderwiki.web.domain;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_WIKI_BAK")
public class WeWikiBak extends BaseObjectBean{

 private  long serialVersionUID;

@Column(name = "we_bak_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_bak_idx;

@Column(name = "we_wiki_idx", primaryKey = true)
 private  Integer we_wiki_idx;

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

@Column(name = "we_user_ip")
 private  String we_user_ip;

@Column(name = "we_wiki_protect")
 private  String we_wiki_protect;

@Column(name = "we_edit_text")
 private  String we_edit_text;

@Column(name = "we_use_yn")
 private  String we_use_yn;

@Column(name = "we_ins_user")
 private  Integer we_ins_user;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

@Column(name = "we_upd_user")
 private  Integer we_upd_user;

@Column(name = "we_upd_date")
 private  Date we_upd_date;

@Column(name = "we_mov_date")
 private  Date we_mov_date;

 private  String we_text_br;

 private  String we_user_nick;


public Integer getWe_upd_user(){
    return we_upd_user;
}


public void setWe_edit_text(String we_edit_text){
    this.we_edit_text = we_edit_text;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
}


public void setWe_bak_idx(Integer we_bak_idx){
    this.we_bak_idx = we_bak_idx;
}


public String getWe_wiki_title(){
    return we_wiki_title;
}


public void setWe_wiki_markup(String we_wiki_markup){
    this.we_wiki_markup = we_wiki_markup;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public String getWe_user_ip(){
    return we_user_ip;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_edit_text(){
    return we_edit_text;
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


public String getWe_text_br(){
    return we_text_br;
}


public String getWe_wiki_status(){
    return we_wiki_status;
}


public void setWe_wiki_title(String we_wiki_title){
    this.we_wiki_title = we_wiki_title;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_bak_idx(){
    return we_bak_idx;
}


public void setWe_wiki_text(String we_wiki_text){
    this.we_wiki_text = we_wiki_text;
}


public String getWe_wiki_text(){
    return we_wiki_text;
}


public void setWe_text_br(String we_text_br){
    this.we_text_br = we_text_br;
}


public String getWe_user_nick(){
    return we_user_nick;
}


public Date getWe_mov_date(){
    return we_mov_date;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public void setWe_mov_date(Date we_mov_date){
    this.we_mov_date = we_mov_date;
}


public void setWe_ins_user(Integer we_ins_user){
    this.we_ins_user = we_ins_user;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public void setWe_user_nick(String we_user_nick){
    this.we_user_nick = we_user_nick;
}


public void setWe_wiki_protect(String we_wiki_protect){
    this.we_wiki_protect = we_wiki_protect;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public String getWe_wiki_markup(){
    return we_wiki_markup;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


@Override
public String toString(){
    return "WeWikiBak [we_bak_idx=" + we_bak_idx + ", we_wiki_idx=" + we_wiki_idx + ", we_wiki_title=" + we_wiki_title + ", we_wiki_text=" + we_wiki_text + ", we_wiki_markup=" + we_wiki_markup + ", we_wiki_revision=" + we_wiki_revision + ", we_wiki_status=" + we_wiki_status + ", we_user_ip=" + we_user_ip + ", we_wiki_protect=" + we_wiki_protect + ", we_edit_text=" + we_edit_text + ", we_use_yn=" + we_use_yn + ", we_ins_user=" + we_ins_user + ", we_ins_date=" + we_ins_date + ", we_upd_user=" + we_upd_user + ", we_upd_date=" + we_upd_date + ", we_mov_date=" + we_mov_date + ", toString()=" + super.toString() + "]";
}


public void setWe_user_ip(String we_user_ip){
    this.we_user_ip = we_user_ip;
}


}