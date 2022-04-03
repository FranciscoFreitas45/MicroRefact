package org.gliderwiki.web.domain;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_CATEGORY")
public class WeCategory extends BaseObjectBean{

@Column(name = "we_category_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_category_idx;

@Column(name = "we_category_kind")
 private  String we_category_kind;

@Column(name = "we_category_name")
 private  String we_category_name;

@Column(name = "we_parent_cate_id")
 private  Integer we_parent_cate_id;

@Column(name = "we_category_sort")
 private  Integer we_category_sort;

@Column(name = "we_space_idx")
 private  Integer we_space_idx;

@Column(name = "we_use_yn")
 private  String we_use_yn;


public void setWe_space_idx(Integer we_space_idx){
    this.we_space_idx = we_space_idx;
}


public Integer getWe_space_idx(){
    return we_space_idx;
}


public String getWe_category_name(){
    return we_category_name;
}


public void setWe_category_sort(Integer we_category_sort){
    this.we_category_sort = we_category_sort;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_category_idx(Integer we_category_idx){
    this.we_category_idx = we_category_idx;
}


public String getWe_category_kind(){
    return we_category_kind;
}


public void setWe_category_name(String we_category_name){
    this.we_category_name = we_category_name;
}


public Integer getWe_category_sort(){
    return we_category_sort;
}


public void setWe_category_kind(String we_category_kind){
    this.we_category_kind = we_category_kind;
}


public Integer getWe_parent_cate_id(){
    return we_parent_cate_id;
}


public void setWe_parent_cate_id(Integer we_parent_cate_id){
    this.we_parent_cate_id = we_parent_cate_id;
}


public Integer getWe_category_idx(){
    return we_category_idx;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


}