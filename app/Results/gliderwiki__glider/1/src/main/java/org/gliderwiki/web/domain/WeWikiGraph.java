package org.gliderwiki.web.domain;
 import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_WIKI_GRAPH")
@DataTransferObject
public class WeWikiGraph extends BaseObjectBean{

 private  long serialVersionUID;

@Column(name = "we_graph_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_graph_idx;

@Column(name = "we_wiki_idx")
 private  Integer we_wiki_idx;

@Column(name = "we_wiki_revision")
 private  Integer we_wiki_revision;

@Column(name = "we_graph_cnt")
 private  Integer we_graph_cnt;

@Column(name = "we_use_yn")
 private  String we_use_yn;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public void setWe_graph_cnt(Integer we_graph_cnt){
    this.we_graph_cnt = we_graph_cnt;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public Integer getWe_graph_idx(){
    return we_graph_idx;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public void setWe_graph_idx(Integer we_graph_idx){
    this.we_graph_idx = we_graph_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


public Integer getWe_graph_cnt(){
    return we_graph_cnt;
}


}