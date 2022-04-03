package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiBak extends BaseObjectBean{

 private  long serialVersionUID;

 private  Integer we_bak_idx;

 private  Integer we_wiki_idx;

 private  String we_wiki_title;

 private  String we_wiki_text;

 private  String we_wiki_markup;

 private  Integer we_wiki_revision;

 private  String we_wiki_status;

 private  String we_user_ip;

 private  String we_wiki_protect;

 private  String we_edit_text;

 private  String we_use_yn;

 private  Integer we_ins_user;

 private  Date we_ins_date;

 private  Integer we_upd_user;

 private  Date we_upd_date;

 private  Date we_mov_date;

 private  String we_text_br;

 private  String we_user_nick;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public Integer getWe_upd_user(){
    return we_upd_user;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_wiki_title(){
    return we_wiki_title;
}


public String getWe_user_ip(){
    return we_user_ip;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_edit_text(){
    return we_edit_text;
}


public String getWe_wiki_protect(){
    return we_wiki_protect;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_text_br(){
    return we_text_br;
}


public String getWe_wiki_status(){
    return we_wiki_status;
}


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_bak_idx(){
    return we_bak_idx;
}


public String getWe_wiki_text(){
    return we_wiki_text;
}


public String getWe_user_nick(){
    return we_user_nick;
}


public Date getWe_mov_date(){
    return we_mov_date;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public String getWe_wiki_markup(){
    return we_wiki_markup;
}


public void setWe_edit_text(String we_edit_text){
    this.we_edit_text = we_edit_text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_edit_text"))

.queryParam("we_edit_text",we_edit_text)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_mov_date(Date we_mov_date){
    this.we_mov_date = we_mov_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_mov_date"))

.queryParam("we_mov_date",we_mov_date)
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


public void setWe_upd_user(Integer we_upd_user){
    this.we_upd_user = we_upd_user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_upd_user"))

.queryParam("we_upd_user",we_upd_user)
;
restTemplate.put(builder.toUriString(),null);
}


@Override
public String toString(){
    return "WeWikiBak [we_bak_idx=" + we_bak_idx + ", we_wiki_idx=" + we_wiki_idx + ", we_wiki_title=" + we_wiki_title + ", we_wiki_text=" + we_wiki_text + ", we_wiki_markup=" + we_wiki_markup + ", we_wiki_revision=" + we_wiki_revision + ", we_wiki_status=" + we_wiki_status + ", we_user_ip=" + we_user_ip + ", we_wiki_protect=" + we_wiki_protect + ", we_edit_text=" + we_edit_text + ", we_use_yn=" + we_use_yn + ", we_ins_user=" + we_ins_user + ", we_ins_date=" + we_ins_date + ", we_upd_user=" + we_upd_user + ", we_upd_date=" + we_upd_date + ", we_mov_date=" + we_mov_date + ", toString()=" + super.toString() + "]";
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toString"))

;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}