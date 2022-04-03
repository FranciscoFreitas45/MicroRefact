package org.gliderwiki.web.domain;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_SEND_MAIL")
public class WeSendMail extends BaseObjectBean{

@Column(primaryKey = true, autoIncrement = true)
 private  Integer we_mail_idx;

@Column(name = "we_user_idx")
 private  Integer we_user_idx;

@Column(name = "we_send_status")
 private  String we_send_status;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

@Column(name = "we_upd_date")
 private  Date we_upd_date;

@Column(name = "we_meta_domain")
 private  String we_meta_domain;

@Column(name = "we_log_param")
 private  String we_log_param;

public WeSendMail() {
}public WeSendMail(Integer we_user_idx, String we_send_status, String we_meta_domain) {
    this.we_user_idx = we_user_idx;
    this.we_send_status = we_send_status;
    this.we_meta_domain = we_meta_domain;
}
public void setWe_log_param(String we_log_param){
    this.we_log_param = we_log_param;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public String getWe_meta_domain(){
    return we_meta_domain;
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_log_param(){
    return we_log_param;
}


public void setWe_send_status(String we_send_status){
    this.we_send_status = we_send_status;
}


public String getWe_send_status(){
    return we_send_status;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
}


public void setWe_mail_idx(Integer we_mail_idx){
    this.we_mail_idx = we_mail_idx;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_meta_domain(String we_meta_domain){
    this.we_meta_domain = we_meta_domain;
}


public Integer getWe_mail_idx(){
    return we_mail_idx;
}


}