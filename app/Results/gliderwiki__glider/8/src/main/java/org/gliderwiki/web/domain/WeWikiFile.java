package org.gliderwiki.web.domain;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_WIKI_FILE")
public class WeWikiFile extends BaseObjectBean{

@Column(name = "we_file_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_file_idx;

@Column(name = "we_wiki_idx")
 private  Integer we_wiki_idx;

@Column(name = "we_file_real_name")
 private  String we_file_real_name;

@Column(name = "we_file_save_name")
 private  String we_file_save_name;

@Column(name = "we_file_save_path")
 private  String we_file_save_path;

@Column(name = "we_file_type")
 private  String we_file_type;

@Column(name = "we_thumb_path")
 private  String we_thumb_path;

@Column(name = "we_thumb_name")
 private  String we_thumb_name;

@Column(name = "we_file_size")
 private  Long we_file_size;

@Column(name = "we_wiki_revision")
 private  Integer we_wiki_revision;

@Column(name = "we_file_down")
 private  Integer we_file_down;

@Column(name = "we_use_yn")
 private  String we_use_yn;


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public void setWe_file_save_name(String we_file_save_name){
    this.we_file_save_name = we_file_save_name;
}


public void setWe_file_size(Long we_file_size){
    this.we_file_size = we_file_size;
}


public String getWe_thumb_path(){
    return we_thumb_path;
}


public void setWe_thumb_name(String we_thumb_name){
    this.we_thumb_name = we_thumb_name;
}


public Integer getWe_file_idx(){
    return we_file_idx;
}


public String getWe_thumb_name(){
    return we_thumb_name;
}


public Long getWe_file_size(){
    return we_file_size;
}


public String getWe_file_save_name(){
    return we_file_save_name;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_file_save_path(String we_file_save_path){
    this.we_file_save_path = we_file_save_path;
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
}


public String getWe_file_type(){
    return we_file_type;
}


public Integer getWe_file_down(){
    return we_file_down;
}


public void setWe_file_real_name(String we_file_real_name){
    this.we_file_real_name = we_file_real_name;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_file_real_name(){
    return we_file_real_name;
}


public void setWe_file_type(String we_file_type){
    this.we_file_type = we_file_type;
}


public void setWe_file_down(Integer we_file_down){
    this.we_file_down = we_file_down;
}


public String getWe_file_save_path(){
    return we_file_save_path;
}


public void setWe_thumb_path(String we_thumb_path){
    this.we_thumb_path = we_thumb_path;
}


public void setWe_file_idx(Integer we_file_idx){
    this.we_file_idx = we_file_idx;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


}