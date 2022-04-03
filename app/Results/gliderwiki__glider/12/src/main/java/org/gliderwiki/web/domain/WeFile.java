package org.gliderwiki.web.domain;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
import com.google.gson.annotations.Expose;
@Table("WE_FILE")
public class WeFile extends BaseObjectBean{

@Column(primaryKey = true, autoIncrement = true)
 private  Integer we_file_idx;

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

@Column(name = "we_thumb_yn")
 private  String we_thumb_yn;

@Column(name = "we_file_size")
 private  String we_file_size;

@Column(name = "we_user_idx")
 private  Integer we_user_idx;

@Column(name = "we_ins_date")
 private  String we_ins_date;

@Column(name = "we_ins_user")
 private  String we_ins_user;


public void setWe_file_save_name(String we_file_save_name){
    this.we_file_save_name = we_file_save_name;
}


public void setWe_file_size(String we_file_size){
    this.we_file_size = we_file_size;
}


public String getWe_thumb_path(){
    return we_thumb_path;
}


public void setWe_thumb_yn(String we_thumb_yn){
    this.we_thumb_yn = we_thumb_yn;
}


public void setWe_thumb_name(String we_thumb_name){
    this.we_thumb_name = we_thumb_name;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public Integer getWe_file_idx(){
    return we_file_idx;
}


public String getWe_thumb_name(){
    return we_thumb_name;
}


public String getWe_file_size(){
    return we_file_size;
}


public String getWe_file_save_name(){
    return we_file_save_name;
}


public void setWe_file_save_path(String we_file_save_path){
    this.we_file_save_path = we_file_save_path;
}


public void setWe_ins_user(String we_ins_user){
    this.we_ins_user = we_ins_user;
}


public String getWe_ins_user(){
    return we_ins_user;
}


public String getWe_ins_date(){
    return we_ins_date;
}


public String getWe_file_type(){
    return we_file_type;
}


public String getWe_thumb_yn(){
    return we_thumb_yn;
}


public void setWe_file_real_name(String we_file_real_name){
    this.we_file_real_name = we_file_real_name;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
}


public String getWe_file_real_name(){
    return we_file_real_name;
}


public void setWe_ins_date(String we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_file_type(String we_file_type){
    this.we_file_type = we_file_type;
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


}