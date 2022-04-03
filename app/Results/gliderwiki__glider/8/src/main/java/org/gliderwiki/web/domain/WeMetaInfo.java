package org.gliderwiki.web.domain;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_META_INFO")
public class WeMetaInfo extends BaseObjectBean{

@Column(name = "we_meta_idx", primaryKey = true, autoIncrement = true)
 public  Integer we_meta_idx;

@Column(name = "we_meta_type")
 public  String we_meta_type;

@Column(name = "we_meta_domain")
 public  String we_meta_domain;

@Column(name = "we_meta_desc")
 public  String we_meta_desc;

@Column(name = "we_meta_table_name")
 public  String we_meta_table_name;

@Column(name = "we_meta_table_key")
 public  String we_meta_table_key;

@Column(name = "we_use_yn")
 public  String we_use_yn;


public String getWe_meta_domain(){
    return we_meta_domain;
}


public void setWe_meta_table_key(String we_meta_table_key){
    this.we_meta_table_key = we_meta_table_key;
}


public String getWe_meta_table_key(){
    return we_meta_table_key;
}


public void setWe_meta_idx(Integer we_meta_idx){
    this.we_meta_idx = we_meta_idx;
}


public String getWe_meta_table_name(){
    return we_meta_table_name;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_meta_type(String we_meta_type){
    this.we_meta_type = we_meta_type;
}


public String getWe_meta_desc(){
    return we_meta_desc;
}


public void setWe_meta_table_name(String we_meta_table_name){
    this.we_meta_table_name = we_meta_table_name;
}


public Integer getWe_meta_idx(){
    return we_meta_idx;
}


public void setWe_meta_domain(String we_meta_domain){
    this.we_meta_domain = we_meta_domain;
}


public void setWe_meta_desc(String we_meta_desc){
    this.we_meta_desc = we_meta_desc;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


public String getWe_meta_type(){
    return we_meta_type;
}


}