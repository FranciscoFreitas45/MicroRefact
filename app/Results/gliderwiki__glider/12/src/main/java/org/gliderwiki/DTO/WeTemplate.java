package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeTemplate extends BaseObjectBean{

 private  Integer we_template_idx;

 private  String we_template_type;

 private  String we_template_name;

 private  String we_template_text;

 private  String we_template_markup;

 private  String we_use_yn;

 private  Integer we_ins_user;

 private  Date we_ins_date;

 private  Integer we_upd_user;

 private  Date we_upd_date;

 private  String we_user_nick;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Integer getWe_upd_user(){
    return we_upd_user;
}


public void setWe_template_text(String we_template_text){
    this.we_template_text = we_template_text;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_user_nick(){
    return we_user_nick;
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public Integer getWe_template_idx(){
    return we_template_idx;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public String getWe_template_type(){
    return we_template_type;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_template_name(){
    return we_template_name;
}


public String getWe_template_markup(){
    return we_template_markup;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public String getWe_template_text(){
    return we_template_text;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_use_yn"))

.queryParam("we_use_yn",we_use_yn)
;
restTemplate.put(builder.toUriString(),null);
}


}