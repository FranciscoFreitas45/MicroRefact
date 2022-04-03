package org.gliderwiki.web.domain;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_ACCESS")
public class WeAccess extends BaseObjectBean{

@Column(name = "we_access_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_access_idx;

@Column(name = "we_target_ip")
 private  String we_target_ip;

@Column(name = "we_ins_user")
 private  Integer we_ins_user;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

@Column(name = "we_use_yn")
 private  String we_use_yn;

public WeAccess() {
}public WeAccess(Integer we_access_idx) {
    this.we_access_idx = we_access_idx;
}public WeAccess(String we_target_ip, Integer we_ins_user) {
    this.we_target_ip = we_target_ip;
    this.we_ins_user = we_ins_user;
    this.we_ins_date = new Date();
}
public Integer getWe_access_idx(){
    return we_access_idx;
}


public void setWe_access_idx(Integer we_access_idx){
    this.we_access_idx = we_access_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_target_ip(String we_target_ip){
    this.we_target_ip = we_target_ip;
}


public void setWe_ins_user(Integer we_ins_user){
    this.we_ins_user = we_ins_user;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


public String getWe_target_ip(){
    return we_target_ip;
}


}