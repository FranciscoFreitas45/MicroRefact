package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeInstallUser extends BaseObjectBean{

 private  Integer we_install_idx;

 private  String we_email;

 private  String we_domain;

 private  String we_active_key;

 private  String we_auth_yn;

 private  String we_new_yn;

 private  String we_use_purpose;

 private  String we_company;

 private  Date we_install_date;

 private  Date we_auth_date;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public Date getWe_install_date(){
    return we_install_date;
}


public Date getWe_auth_date(){
    return we_auth_date;
}


public String getWe_domain(){
    return we_domain;
}


public Integer getWe_install_idx(){
    return we_install_idx;
}


public String getWe_new_yn(){
    return we_new_yn;
}


public String getWe_active_key(){
    return we_active_key;
}


public String getWe_use_purpose(){
    return we_use_purpose;
}


public String getWe_company(){
    return we_company;
}


public String getWe_email(){
    return we_email;
}


public String getWe_auth_yn(){
    return we_auth_yn;
}


public void setWe_install_date(Date we_install_date){
    this.we_install_date = we_install_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_install_date"))

.queryParam("we_install_date",we_install_date)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_active_key(String we_active_key){
    this.we_active_key = we_active_key;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_active_key"))

.queryParam("we_active_key",we_active_key)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_auth_date(Date we_auth_date){
    this.we_auth_date = we_auth_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_auth_date"))

.queryParam("we_auth_date",we_auth_date)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_auth_yn(String we_auth_yn){
    this.we_auth_yn = we_auth_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_auth_yn"))

.queryParam("we_auth_yn",we_auth_yn)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_domain(String we_domain){
    this.we_domain = we_domain;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_domain"))

.queryParam("we_domain",we_domain)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_email(String we_email){
    this.we_email = we_email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_email"))

.queryParam("we_email",we_email)
;
restTemplate.put(builder.toUriString(),null);
}


}