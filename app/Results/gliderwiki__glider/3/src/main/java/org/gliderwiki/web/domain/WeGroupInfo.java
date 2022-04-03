package org.gliderwiki.web.domain;
 import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_GROUP_INFO")
@DataTransferObject
public class WeGroupInfo extends BaseObjectBean{

@Column(name = "we_group_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_group_idx;

@Column(name = "we_group_code")
 private  String we_group_code;

@Column(name = "we_group_name")
 private  String we_group_name;

@Column(name = "we_group_type")
 private  String we_group_type;

@Column(name = "we_group_owner")
 private  String we_group_owner;

@Column(name = "we_required_yn")
 private  String we_required_yn;

@Column(name = "we_group_info")
 private  String we_group_info;

@Column(name = "we_use_yn")
 private  String we_use_yn;

@Column(name = "we_ins_user")
 private  String we_ins_user;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

@Column(name = "we_upd_user")
 private  String we_upd_user;

@Column(name = "we_upd_date")
 private  Date we_upd_date;

 private  String we_user_nick;


public void setWe_group_code(String we_group_code){
    this.we_group_code = we_group_code;
}


public String getWe_upd_user(){
    return we_upd_user;
}


public void setWe_group_info(String we_group_info){
    this.we_group_info = we_group_info;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
}


public String getWe_required_yn(){
    return we_required_yn;
}


public String getWe_group_name(){
    return we_group_name;
}


public void setWe_group_type(String we_group_type){
    this.we_group_type = we_group_type;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public void setWe_upd_user(String we_upd_user){
    this.we_upd_user = we_upd_user;
}


public void setWe_group_name(String we_group_name){
    this.we_group_name = we_group_name;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


public Integer getWe_group_idx(){
    return we_group_idx;
}


public String getWe_group_info(){
    return we_group_info;
}


public void setWe_group_idx(Integer we_group_idx){
    this.we_group_idx = we_group_idx;
}


public String getWe_user_nick(){
    return we_user_nick;
}


public void setWe_ins_user(String we_ins_user){
    this.we_ins_user = we_ins_user;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public String getWe_ins_user(){
    return we_ins_user;
}


public void setWe_user_nick(String we_user_nick){
    this.we_user_nick = we_user_nick;
}


public String getWe_group_code(){
    return we_group_code;
}


public void setWe_required_yn(String we_required_yn){
    this.we_required_yn = we_required_yn;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public String getWe_group_type(){
    return we_group_type;
}


public void setWe_group_owner(String we_group_owner){
    this.we_group_owner = we_group_owner;
}


public String getWe_group_owner(){
    return we_group_owner;
}


}