package org.gliderwiki.web.domain;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiLog extends BaseObjectBean{

@Column(name = "we_wiki_idx")
 private  Integer we_wiki_idx;

@Column(name = "we_wiki_status")
 private  String we_wiki_status;

@Column(name = "we_user_idx")
 private  Integer we_user_idx;

@Column(name = "we_wiki_revision")
 private  Integer we_wiki_revision;

@Column(name = "we_wiki_action_type")
 private  String we_wiki_action_type;

@Column(name = "we_ins_date")
 private  Date we_ins_date;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public void setWe_wiki_status(String we_wiki_status){
    this.we_wiki_status = we_wiki_status;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
}


public String getWe_wiki_action_type(){
    return we_wiki_action_type;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_wiki_status(){
    return we_wiki_status;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_wiki_action_type(String we_wiki_action_type){
    this.we_wiki_action_type = we_wiki_action_type;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


}