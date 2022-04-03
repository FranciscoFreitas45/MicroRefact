package org.gliderwiki.web.domain;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_USER_ALARM")
public class WeUserAlarm extends BaseObjectBean{

@Column(primaryKey = true)
 private  Integer we_user_idx;

@Column(name = "we_meta_idx")
 private  Integer we_meta_idx;

@Column(name = "we_use_yn")
 private  String we_use_yn;

@Column(name = "we_ins_date")
 private  String we_ins_date;

@Column(name = "we_ins_user")
 private  String we_ins_user;


public Integer getWe_meta_idx(){
    return we_meta_idx;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
}


public void setWe_meta_idx(Integer we_meta_idx){
    this.we_meta_idx = we_meta_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_ins_date(String we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_ins_user(String we_ins_user){
    this.we_ins_user = we_ins_user;
}


public String getWe_ins_user(){
    return we_ins_user;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


public String getWe_ins_date(){
    return we_ins_date;
}


}