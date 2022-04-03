package org.gliderwiki.DTO;
 import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiLog extends BaseObjectBean{

 private  Integer we_wiki_idx;

 private  String we_wiki_status;

 private  Integer we_user_idx;

 private  Integer we_wiki_revision;

 private  String we_wiki_action_type;

 private  Date we_ins_date;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public String getWe_wiki_action_type(){
    return we_wiki_action_type;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_wiki_status(){
    return we_wiki_status;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public void setWe_wiki_idx(Integer we_wiki_idx){
    this.we_wiki_idx = we_wiki_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_idx"))

.queryParam("we_wiki_idx",we_wiki_idx)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_wiki_revision(Integer we_wiki_revision){
    this.we_wiki_revision = we_wiki_revision;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_revision"))

.queryParam("we_wiki_revision",we_wiki_revision)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_wiki_status(String we_wiki_status){
    this.we_wiki_status = we_wiki_status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_status"))

.queryParam("we_wiki_status",we_wiki_status)
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


public void setWe_wiki_action_type(String we_wiki_action_type){
    this.we_wiki_action_type = we_wiki_action_type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_action_type"))

.queryParam("we_wiki_action_type",we_wiki_action_type)
;
restTemplate.put(builder.toUriString(),null);
}


}