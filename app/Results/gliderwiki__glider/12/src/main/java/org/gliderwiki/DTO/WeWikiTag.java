package org.gliderwiki.DTO;
 import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiTag extends BaseObjectBean{

 private  Integer we_wiki_tag_idx;

 private  Integer we_wiki_idx;

 private  Integer we_wiki_revision;

 private  String we_tag;

 private  String we_ins_date;

 private  String we_use_yn;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_wiki_tag_idx(){
    return we_wiki_tag_idx;
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


public String getWe_tag(){
    return we_tag;
}


public String getWe_ins_date(){
    return we_ins_date;
}


}