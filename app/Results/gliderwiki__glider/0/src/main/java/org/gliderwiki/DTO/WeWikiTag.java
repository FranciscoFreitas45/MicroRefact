package org.gliderwiki.DTO;
 import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeWikiTag extends BaseObjectBean{

 private  Integer we_wiki_tag_idx;

 private  Integer we_wiki_idx;

 private  Integer we_wiki_revision;

 private  String we_tag;

 private  String we_ins_date;

 private  String we_use_yn;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public Integer getWe_wiki_idx(){
    return we_wiki_idx;
}


public Integer getWe_wiki_tag_idx(){
    return we_wiki_tag_idx;
}


public Integer getWe_wiki_revision(){
    return we_wiki_revision;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_tag(){
    return we_tag;
}


public String getWe_ins_date(){
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


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_use_yn"))

.queryParam("we_use_yn",we_use_yn)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWe_wiki_tag_idx(Integer we_wiki_tag_idx){
    this.we_wiki_tag_idx = we_wiki_tag_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_wiki_tag_idx"))

.queryParam("we_wiki_tag_idx",we_wiki_tag_idx)
;
restTemplate.put(builder.toUriString(),null);
}


}