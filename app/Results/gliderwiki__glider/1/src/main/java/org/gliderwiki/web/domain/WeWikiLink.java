package org.gliderwiki.web.domain;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_WIKI_LINK")
public class WeWikiLink extends BaseObjectBean{

@Column(name = "we_link_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_link_idx;

@Column(name = "we_link_url")
 private  String we_link_url;

@Column(name = "we_link_title")
 private  String we_link_title;

@Column(name = "we_link_text")
 private  String we_link_text;

@Column(name = "we_wiki_idx")
 private  Integer we_wiki_idx;

@Column(name = "we_wiki_revision")
 private  Integer we_wiki_revision;

@Column(name = "we_use_yn")
 private  String we_use_yn;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_link_idx(){
    return we_link_idx;
}


public void setWe_link_url(String we_link_url){
    this.we_link_url = we_link_url;
}


public String getWe_link_text(){
    return we_link_text;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public void setWe_link_title(String we_link_title){
    this.we_link_title = we_link_title;
}


public String getWe_link_url(){
    return we_link_url;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_link_title(){
    return we_link_title;
}


public void setWe_link_text(String we_link_text){
    this.we_link_text = we_link_text;
}


public void setWe_link_idx(Integer we_link_idx){
    this.we_link_idx = we_link_idx;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


}