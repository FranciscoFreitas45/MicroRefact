package org.gliderwiki.DTO;
 import java.io.Serializable;
import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeBbsComment extends BaseObjectBeanimplements Serializable{

 private  long serialVersionUID;

 private  Integer we_bbs_comment_idx;

 private  Integer we_bbs_idx;

 private  String we_user_ip;

 private  String we_bbs_text;

 private  Integer we_ins_user;

 private  Date we_ins_date;

 private  String we_ins_name;

 private  String we_use_yn;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public WeBbsComment() {
}
public Integer getWe_bbs_idx(){
    return we_bbs_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public String getWe_user_ip(){
    return we_user_ip;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_ins_name(){
    return we_ins_name;
}


public Integer getWe_bbs_comment_idx(){
    return we_bbs_comment_idx;
}


public String getWe_bbs_text(){
    return we_bbs_text;
}


public void setWe_bbs_comment_idx(Integer we_bbs_comment_idx){
    this.we_bbs_comment_idx = we_bbs_comment_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_bbs_comment_idx"))

.queryParam("we_bbs_comment_idx",we_bbs_comment_idx)
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