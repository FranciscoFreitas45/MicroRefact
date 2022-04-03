package org.gliderwiki.web.domain;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_WIKI_NOTE")
public class WeWikiNote extends BaseObjectBean{

@Column(name = "we_wiki_note_num", primaryKey = true, autoIncrement = true)
 private  Integer we_wiki_note_num;

@Column(name = "we_wiki_note_name")
 private  String we_wiki_note_name;

@Column(name = "we_wiki_note_desc")
 private  String we_wiki_note_desc;

@Column(name = "we_wiki_idx")
 private  Integer we_wiki_idx;

@Column(name = "we_wiki_revision")
 private  Integer we_wiki_revision;

@Column(name = "we_use_yn")
 private  String we_use_yn;


public void setWe_wiki_note_desc(String we_wiki_note_desc){
    this.we_wiki_note_desc = we_wiki_note_desc;
}


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public String getWe_wiki_note_desc(){
    return we_wiki_note_desc;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public Integer getWe_wiki_note_num(){
    return we_wiki_note_num;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_wiki_note_name(){
    return we_wiki_note_name;
}


public void setWe_wiki_note_name(String we_wiki_note_name){
    this.we_wiki_note_name = we_wiki_note_name;
}


public void setWe_wiki_note_num(Integer we_wiki_note_num){
    this.we_wiki_note_num = we_wiki_note_num;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


}