package org.gliderwiki.DTO;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiSummary extends BaseObjectBean{

 private  Integer we_summary_idx;

 private  Integer we_wiki_idx;

 private  Integer we_wiki_revision;

 private  String we_summary_title;

 private  String we_summary_tag;

 private  String we_use_yn;


public Integer getWe_summary_idx(){
    return we_summary_idx;
}


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
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


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public String getWe_summary_tag(){
    return we_summary_tag;
}


}