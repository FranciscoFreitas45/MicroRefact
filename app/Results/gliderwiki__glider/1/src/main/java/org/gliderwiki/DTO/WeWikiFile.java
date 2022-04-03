package org.gliderwiki.DTO;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiFile extends BaseObjectBean{

 private  Integer we_file_idx;

 private  Integer we_wiki_idx;

 private  String we_file_real_name;

 private  String we_file_save_name;

 private  String we_file_save_path;

 private  String we_file_type;

 private  String we_thumb_path;

 private  String we_thumb_name;

 private  Long we_file_size;

 private  Integer we_wiki_revision;

 private  Integer we_file_down;

 private  String we_use_yn;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public String getWe_thumb_path(){
    return we_thumb_path;
}


public Integer getWe_file_idx(){
    return we_file_idx;
}


public String getWe_thumb_name(){
    return we_thumb_name;
}


public Long getWe_file_size(){
    return we_file_size;
}


public String getWe_file_save_name(){
    return we_file_save_name;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_file_type(){
    return we_file_type;
}


public Integer getWe_file_down(){
    return we_file_down;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_file_real_name(){
    return we_file_real_name;
}


public String getWe_file_save_path(){
    return we_file_save_path;
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


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_use_yn"))

.queryParam("we_use_yn",we_use_yn)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_file_idx(Integer we_file_idx){
    this.we_file_idx = we_file_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_file_idx"))

.queryParam("we_file_idx",we_file_idx)
;
restTemplate.put(builder.toUriString(),null);
}


}