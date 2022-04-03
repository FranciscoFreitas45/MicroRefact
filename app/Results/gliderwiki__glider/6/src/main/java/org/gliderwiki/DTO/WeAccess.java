package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeAccess extends BaseObjectBean{

 private  Integer we_access_idx;

 private  String we_target_ip;

 private  Integer we_ins_user;

 private  Date we_ins_date;

 private  String we_use_yn;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

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


public String getWe_use_yn(){
    return we_use_yn;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_target_ip(){
    return we_target_ip;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_use_yn"))

.queryParam("we_use_yn",we_use_yn)
;
restTemplate.put(builder.toUriString(),null);
}


}