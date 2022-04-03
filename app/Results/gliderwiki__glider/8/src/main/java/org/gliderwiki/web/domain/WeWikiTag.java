package org.gliderwiki.web.domain;
 import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@SuppressWarnings("serial")
@Table("WE_WIKI_TAG")
@DataTransferObject
public class WeWikiTag extends BaseObjectBean{

@Column(name = "we_wiki_tag_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_wiki_tag_idx;

@Column(name = "we_wiki_idx")
 private  Integer we_wiki_idx;

@Column(name = "we_wiki_revision")
 private  Integer we_wiki_revision;

@Column(name = "we_tag")
 private  String we_tag;

@Column(name = "we_ins_date")
 private  String we_ins_date;

@Column(name = "we_use_yn")
 private  String we_use_yn;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_wiki_tag_idx(){
    return we_wiki_tag_idx;
}


public void setWe_tag(String we_tag){
    this.we_tag = we_tag;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public void setWe_wiki_tag_idx(Integer we_wiki_tag_idx){
    this.we_wiki_tag_idx = we_wiki_tag_idx;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_ins_date(String we_ins_date){
    this.we_ins_date = we_ins_date;
}


public String getWe_tag(){
    return we_tag;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public String getWe_ins_date(){
    return we_ins_date;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


}