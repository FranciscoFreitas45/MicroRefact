package org.gliderwiki.DTO;
 import java.io.Serializable;
import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
public class WeAddFriend implements Serializable{

 private  long serialVersionUID;

 private  Integer we_user_idx;

 private  Integer we_target_user_idx;

 private  Date we_add_date;

 private  Date we_del_date;

 private  String we_use_yn;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public Integer getWe_user_idx(){
    return we_user_idx;
}


public Date getWe_add_date(){
    return we_add_date;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public Integer getWe_target_user_idx(){
    return we_target_user_idx;
}


public Date getWe_del_date(){
    return we_del_date;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_idx"))

.queryParam("we_user_idx",we_user_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_target_user_idx(Integer we_target_user_idx){
    this.we_target_user_idx = we_target_user_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_target_user_idx"))

.queryParam("we_target_user_idx",we_target_user_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_add_date(Date we_add_date){
    this.we_add_date = we_add_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_add_date"))

.queryParam("we_add_date",we_add_date)
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