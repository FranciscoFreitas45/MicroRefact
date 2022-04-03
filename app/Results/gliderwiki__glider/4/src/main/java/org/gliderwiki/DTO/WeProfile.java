package org.gliderwiki.DTO;
 import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeProfile extends BaseObjectBean{

 private  long serialVersionUID;

 private  Integer we_user_idx;

 private  String we_user_email;

 private  String we_user_site;

 private  String we_cell_num1;

 private  String we_cell_num2;

 private  String we_cell_num3;

 private  String we_file_save_name;

 private  String we_file_real_name;

 private  String we_file_save_path;

 private  String we_thumb_path;

 private  String we_thumb_name;

 private  String we_away_yn;

 private  Integer we_grade;

 private  String we_tech_yn;

 private  Integer we_point;

 private  Date we_visit_date;

 private  Date we_ins_date;

 private  Date we_upd_date;

 private  String we_noti_checked;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";

// 조회용 생성자
public WeProfile(String we_tech_yn, Integer we_point) {
    this.we_tech_yn = we_tech_yn;
    this.we_point = we_point;
}public WeProfile() {
}
public Integer getWe_user_idx(){
    return we_user_idx;
}


public Integer getWe_point(){
    return we_point;
}


public String getWe_cell_num2(){
    return we_cell_num2;
}


public Integer getWe_grade(){
    return we_grade;
}


public String getWe_user_email(){
    return we_user_email;
}


public String getWe_cell_num3(){
    return we_cell_num3;
}


public String getWe_thumb_name(){
    return we_thumb_name;
}


public String getWe_file_save_name(){
    return we_file_save_name;
}


public String getWe_cell_num1(){
    return we_cell_num1;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_file_real_name(){
    return we_file_real_name;
}


public String getWe_file_save_path(){
    return we_file_save_path;
}


public String getWe_away_yn(){
    return we_away_yn;
}


public String getWe_noti_checked(){
    return we_noti_checked;
}


public String getWe_tech_yn(){
    return we_tech_yn;
}


public String getWe_thumb_path(){
    return we_thumb_path;
}


public Date getWe_visit_date(){
    return we_visit_date;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public String getWe_user_site(){
    return we_user_site;
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


public void setWe_user_site(String we_user_site){
    this.we_user_site = we_user_site;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_site"))

.queryParam("we_user_site",we_user_site)
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