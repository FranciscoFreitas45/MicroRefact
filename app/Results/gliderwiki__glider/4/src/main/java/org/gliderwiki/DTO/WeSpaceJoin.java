package org.gliderwiki.DTO;
 import java.io.Serializable;
import java.util.Date;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.framework.util.DateUtil;
public class WeSpaceJoin implements Serializable{

 private  Integer we_space_join_idx;

 private  Integer we_space_idx;

 private  JoinType we_join_type;

 private  Integer we_user_idx;

 private  JoinStatus we_join_status;

 private  String we_ins_date;

 private  Integer we_ins_user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

public WeSpaceJoin() {
}public WeSpaceJoin(Integer we_space_idx, JoinType we_join_type, Integer we_user_idx, JoinStatus we_join_status, Integer we_ins_user) {
    this.we_space_idx = we_space_idx;
    this.we_join_type = we_join_type;
    this.we_user_idx = we_user_idx;
    this.we_join_status = we_join_status;
    this.we_ins_date = DateUtil.getTodayTime();
    this.we_ins_user = we_ins_user;
}
public Integer getWe_space_idx(){
    return we_space_idx;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public JoinStatus getWe_join_status(){
    return we_join_status;
}


public void setWe_ins_user(Integer we_ins_user){
    this.we_ins_user = we_ins_user;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public String getWe_ins_date(){
    return we_ins_date;
}


public Integer getWe_space_join_idx(){
    return we_space_join_idx;
}


public void setWe_space_join_idx(Integer we_space_join_idx){
    this.we_space_join_idx = we_space_join_idx;
}


public void setWe_join_type(JoinType we_join_type){
    this.we_join_type = we_join_type;
}


public JoinType getWe_join_type(){
    return we_join_type;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_user_idx"))

.queryParam("we_user_idx",we_user_idx)
;
restTemplate.put(builder.toUriString(),null);
}


}