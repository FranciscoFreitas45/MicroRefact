package org.gliderwiki.DTO;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiLink extends BaseObjectBean{

 private  Integer we_link_idx;

 private  String we_link_url;

 private  String we_link_title;

 private  String we_link_text;

 private  Integer we_wiki_idx;

 private  Integer we_wiki_revision;

 private  String we_use_yn;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_link_idx(){
    return we_link_idx;
}


public String getWe_link_text(){
    return we_link_text;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_link_title(String we_link_title){
    this.we_link_title = we_link_title;
}


public String getWe_link_url(){
    return we_link_url;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_link_title(){
    return we_link_title;
}


public void setWe_link_idx(Integer we_link_idx){
    this.we_link_idx = we_link_idx;
}


}