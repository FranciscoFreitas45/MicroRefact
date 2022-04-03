package org.gliderwiki.DTO;
 import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeGroupInfo extends BaseObjectBean{

 private  Integer we_group_idx;

 private  String we_group_code;

 private  String we_group_name;

 private  String we_group_type;

 private  String we_group_owner;

 private  String we_required_yn;

 private  String we_group_info;

 private  String we_use_yn;

 private  String we_ins_user;

 private  Date we_ins_date;

 private  String we_upd_user;

 private  Date we_upd_date;

 private  String we_user_nick;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String getWe_upd_user(){
    return we_upd_user;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_required_yn(){
    return we_required_yn;
}


public String getWe_group_name(){
    return we_group_name;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public Integer getWe_group_idx(){
    return we_group_idx;
}


public String getWe_group_info(){
    return we_group_info;
}


public String getWe_user_nick(){
    return we_user_nick;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public String getWe_ins_user(){
    return we_ins_user;
}


public String getWe_group_code(){
    return we_group_code;
}


public String getWe_group_type(){
    return we_group_type;
}


public String getWe_group_owner(){
    return we_group_owner;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_use_yn"))

.queryParam("we_use_yn",we_use_yn)
;
restTemplate.put(builder.toUriString(),null);
}


}