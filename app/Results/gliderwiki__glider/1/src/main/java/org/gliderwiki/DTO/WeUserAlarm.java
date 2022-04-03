package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeUserAlarm extends BaseObjectBean{

 private  Integer we_user_idx;

 private  Integer we_meta_idx;

 private  String we_use_yn;

 private  String we_ins_date;

 private  String we_ins_user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public Integer getWe_meta_idx(){
    return we_meta_idx;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_ins_user(){
    return we_ins_user;
}


public String getWe_ins_date(){
    return we_ins_date;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_idx"))

.queryParam("we_user_idx",we_user_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_meta_idx(Integer we_meta_idx){
    this.we_meta_idx = we_meta_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_meta_idx"))

.queryParam("we_meta_idx",we_meta_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_use_yn"))

.queryParam("we_use_yn",we_use_yn)
;
restTemplate.put(builder.toUriString(),null);
}


}