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


}