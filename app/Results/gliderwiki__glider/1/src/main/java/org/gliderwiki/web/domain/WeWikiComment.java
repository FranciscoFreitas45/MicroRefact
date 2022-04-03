package org.gliderwiki.web.domain;
 import java.io.Serializable;
import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_WIKI_COMMENT")
public class WeWikiComment extends BaseObjectBeanimplements Serializable{

 private  long serialVersionUID;

@Column(name = "we_wiki_comment_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_wiki_comment_idx;

@Column(name = "we_wiki_idx")
 private  Integer we_wiki_idx;

@Column(name = "we_user_ip")
 private  String we_user_ip;

@Column(name = "we_bbs_text")
 private  String we_bbs_text;

@Column(name = "we_ins_user")
 private  Integer we_ins_user;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

@Column(name = "we_ins_name")
 private  String we_ins_name;

@Column(name = "we_use_yn")
 private  String we_use_yn;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_wiki_comment_idx(){
    return we_wiki_comment_idx;
}


public void setWe_bbs_text(String we_bbs_text){
    this.we_bbs_text = we_bbs_text;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_ins_user(Integer we_ins_user){
    this.we_ins_user = we_ins_user;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public void setWe_wiki_comment_idx(Integer we_wiki_comment_idx){
    this.we_wiki_comment_idx = we_wiki_comment_idx;
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


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public void setWe_ins_name(String we_ins_name){
    this.we_ins_name = we_ins_name;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public String getWe_bbs_text(){
    return we_bbs_text;
}


public void setWe_user_ip(String we_user_ip){
    this.we_user_ip = we_user_ip;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


}