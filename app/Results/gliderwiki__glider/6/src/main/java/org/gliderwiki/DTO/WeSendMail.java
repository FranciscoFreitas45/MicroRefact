package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeSendMail extends BaseObjectBean{

 private  Integer we_mail_idx;

 private  Integer we_user_idx;

 private  String we_send_status;

 private  Date we_ins_date;

 private  Date we_upd_date;

 private  String we_meta_domain;

 private  String we_log_param;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";

public WeSendMail() {
}public WeSendMail(Integer we_user_idx, String we_send_status, String we_meta_domain) {
    this.we_user_idx = we_user_idx;
    this.we_send_status = we_send_status;
    this.we_meta_domain = we_meta_domain;
}
public Integer getWe_user_idx(){
    return we_user_idx;
}


public String getWe_meta_domain(){
    return we_meta_domain;
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


public String getWe_send_status(){
    return we_send_status;
}


public Integer getWe_mail_idx(){
    return we_mail_idx;
}


public void setWe_log_param(String we_log_param){
    this.we_log_param = we_log_param;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_log_param"))

.queryParam("we_log_param",we_log_param)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_ins_date"))

.queryParam("we_ins_date",we_ins_date)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_mail_idx(Integer we_mail_idx){
    this.we_mail_idx = we_mail_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_mail_idx"))

.queryParam("we_mail_idx",we_mail_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_send_status(String we_send_status){
    this.we_send_status = we_send_status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_send_status"))

.queryParam("we_send_status",we_send_status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_upd_date"))

.queryParam("we_upd_date",we_upd_date)
;
restTemplate.put(builder.toUriString(),null);
}


}