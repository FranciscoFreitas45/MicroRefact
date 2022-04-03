package org.gliderwiki.DTO;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiNote extends BaseObjectBean{

 private  Integer we_wiki_note_num;

 private  String we_wiki_note_name;

 private  String we_wiki_note_desc;

 private  Integer we_wiki_idx;

 private  Integer we_wiki_revision;

 private  String we_use_yn;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public String getWe_wiki_note_desc(){
    return we_wiki_note_desc;
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


public void setWe_wiki_note_num(Integer we_wiki_note_num){
    this.we_wiki_note_num = we_wiki_note_num;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


}