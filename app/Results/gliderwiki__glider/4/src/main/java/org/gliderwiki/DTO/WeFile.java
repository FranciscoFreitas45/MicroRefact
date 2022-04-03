package org.gliderwiki.DTO;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
import com.google.gson.annotations.Expose;
public class WeFile extends BaseObjectBean{

 private  Integer we_file_idx;

 private  String we_file_real_name;

 private  String we_file_save_name;

 private  String we_file_save_path;

 private  String we_file_type;

 private  String we_thumb_path;

 private  String we_thumb_name;

 private  String we_thumb_yn;

 private  String we_file_size;

 private  Integer we_user_idx;

 private  String we_ins_date;

 private  String we_ins_user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";


public String getWe_thumb_path(){
    return we_thumb_path;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public Integer getWe_file_idx(){
    return we_file_idx;
}


public String getWe_thumb_name(){
    return we_thumb_name;
}


public String getWe_file_size(){
    return we_file_size;
}


public String getWe_file_save_name(){
    return we_file_save_name;
}


public String getWe_ins_user(){
    return we_ins_user;
}


public String getWe_ins_date(){
    return we_ins_date;
}


public String getWe_file_type(){
    return we_file_type;
}


public String getWe_thumb_yn(){
    return we_thumb_yn;
}


public String getWe_file_real_name(){
    return we_file_real_name;
}


public String getWe_file_save_path(){
    return we_file_save_path;
}


public void setWe_file_idx(Integer we_file_idx){
    this.we_file_idx = we_file_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_file_idx"))

.queryParam("we_file_idx",we_file_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_file_real_name(String we_file_real_name){
    this.we_file_real_name = we_file_real_name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_file_real_name"))

.queryParam("we_file_real_name",we_file_real_name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_file_save_name(String we_file_save_name){
    this.we_file_save_name = we_file_save_name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_file_save_name"))

.queryParam("we_file_save_name",we_file_save_name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_file_save_path(String we_file_save_path){
    this.we_file_save_path = we_file_save_path;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_file_save_path"))

.queryParam("we_file_save_path",we_file_save_path)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_file_size(String we_file_size){
    this.we_file_size = we_file_size;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_file_size"))

.queryParam("we_file_size",we_file_size)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_file_type(String we_file_type){
    this.we_file_type = we_file_type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_file_type"))

.queryParam("we_file_type",we_file_type)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_thumb_yn(String we_thumb_yn){
    this.we_thumb_yn = we_thumb_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_thumb_yn"))

.queryParam("we_thumb_yn",we_thumb_yn)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_thumb_name(String we_thumb_name){
    this.we_thumb_name = we_thumb_name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_thumb_name"))

.queryParam("we_thumb_name",we_thumb_name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_thumb_path(String we_thumb_path){
    this.we_thumb_path = we_thumb_path;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_thumb_path"))

.queryParam("we_thumb_path",we_thumb_path)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_idx"))

.queryParam("we_user_idx",we_user_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_ins_date(String we_ins_date){
    this.we_ins_date = we_ins_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_ins_date"))

.queryParam("we_ins_date",we_ins_date)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_ins_user(String we_ins_user){
    this.we_ins_user = we_ins_user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_ins_user"))

.queryParam("we_ins_user",we_ins_user)
;
restTemplate.put(builder.toUriString(),null);
}


}