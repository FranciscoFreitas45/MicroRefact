package org.gliderwiki.web.domain;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_WIKI_SUMMARY")
public class WeWikiSummary extends BaseObjectBean{

@Column(name = "we_summary_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_summary_idx;

@Column(name = "we_wiki_idx")
 private  Integer we_wiki_idx;

@Column(name = "we_wiki_revision")
 private  Integer we_wiki_revision;

@Column(name = "we_summary_title")
 private  String we_summary_title;

@Column(name = "we_summary_tag")
 private  String we_summary_tag;

@Column(name = "we_use_yn")
 private  String we_use_yn;


public Integer getWe_summary_idx(){
    return we_summary_idx;
}


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public void setWe_summary_tag(String we_summary_tag){
    this.we_summary_tag = we_summary_tag;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public void setWe_summary_idx(Integer we_summary_idx){
    this.we_summary_idx = we_summary_idx;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_summary_title(){
    return we_summary_title;
}


public void setWe_summary_title(String we_summary_title){
    this.we_summary_title = we_summary_title;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


public String getWe_summary_tag(){
    return we_summary_tag;
}


}