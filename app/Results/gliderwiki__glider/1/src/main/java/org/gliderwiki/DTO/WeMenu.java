package org.gliderwiki.DTO;
 import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeMenu extends BaseObjectBean{

 private  Integer we_menu_idx;

 private  String we_menu_name;

 private  Integer we_menu_order_idx;

 private  Integer we_menu_parent_idx;

 private  Integer we_menu_depth;

 private  String we_menu_url;

 private  String we_use_yn;

 private  String we_menu_type;

 private  String we_access_level;

 private  String we_menu_group;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public String getWe_menu_group(){
    return we_menu_group;
}


public String getWe_menu_url(){
    return we_menu_url;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public Integer getWe_menu_parent_idx(){
    return we_menu_parent_idx;
}


public Integer getWe_menu_order_idx(){
    return we_menu_order_idx;
}


public String getWe_menu_name(){
    return we_menu_name;
}


public Integer getWe_menu_idx(){
    return we_menu_idx;
}


public Integer getWe_menu_depth(){
    return we_menu_depth;
}


public String getWe_access_level(){
    return we_access_level;
}


public String getWe_menu_type(){
    return we_menu_type;
}


public void setWe_menu_idx(Integer we_menu_idx){
    this.we_menu_idx = we_menu_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_menu_idx"))

.queryParam("we_menu_idx",we_menu_idx)
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