package org.gliderwiki.DTO;
 import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
public class WeSpace extends BaseObjectBean{

 private  long serialVersionUID;

 private  Integer we_space_idx;

 private  String we_space_name;

 private  String we_space_desc;

 private  String we_space_exposed;

 private  AuthorityType we_view_privacy;

 private  AuthorityType we_edit_privacy;

 private  Integer we_admin_idx;

 private  Integer we_ins_user;

 private  Date we_ins_date;

 private  Integer we_upd_user;

 private  Date we_upd_date;

 private  String we_use_yn;

 private  String we_view_data;

 private  String we_edit_data;

 private  String we_view_name;

 private  String we_edit_name;

 private  String we_upload_imgName;

 private  String we_space_admin_nick;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

public WeSpace() {
}public WeSpace(Integer we_space_idx) {
    this.we_space_idx = we_space_idx;
}public WeSpace(Integer we_space_idx, String we_space_name, String we_space_desc, String we_space_exposed, AuthorityType we_view_privacy, AuthorityType we_edit_privacy, Integer we_ins_user, Date we_ins_date, Integer we_admin_idx) {
    this.we_space_idx = we_space_idx;
    this.we_space_name = we_space_name;
    this.we_space_desc = we_space_desc;
    this.we_space_exposed = we_space_exposed;
    this.we_view_privacy = we_view_privacy;
    this.we_edit_privacy = we_edit_privacy;
    this.we_ins_user = we_ins_user;
    this.we_admin_idx = we_ins_user;
    this.we_ins_date = we_ins_date;
    this.we_upd_user = we_ins_user;
    this.we_upd_date = new Date();
    this.we_use_yn = "Y";
}public WeSpace(String we_space_name, String we_space_desc, String we_space_exposed, AuthorityType we_view_privacy, AuthorityType we_edit_privacy, Integer we_admin_idx, Integer we_ins_user) {
    this.we_space_name = we_space_name;
    this.we_space_desc = we_space_desc;
    this.we_space_exposed = we_space_exposed;
    this.we_view_privacy = we_view_privacy;
    this.we_edit_privacy = we_edit_privacy;
    this.we_admin_idx = we_admin_idx;
    this.we_ins_user = we_ins_user;
    this.we_ins_date = new Date();
    this.we_use_yn = "Y";
}
public AuthorityType getWe_view_privacy(){
    return we_view_privacy;
}


public Integer getWe_upd_user(){
    return we_upd_user;
}


public String getWe_edit_data(){
    return we_edit_data;
}


public Integer getWe_admin_idx(){
    return we_admin_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public String getWe_upload_imgName(){
    return we_upload_imgName;
}


public String getWe_space_desc(){
    return we_space_desc;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public String getWe_view_name(){
    return we_view_name;
}


public String getWe_space_exposed(){
    return we_space_exposed;
}


public List<Integer> getSplitedAuthorityData(String authorityData){
    List<Integer> splitedAuthorityData = Lists.newArrayList();
    Iterable<String> data = Splitter.on(',').trimResults().omitEmptyStrings().split(authorityData);
    Iterator<String> iter = data.iterator();
    while (iter.hasNext()) {
        int authorityUser = Integer.parseInt(iter.next());
    }
    return null;
}


public String getWe_edit_name(){
    return we_edit_name;
}


public Integer getWe_space_idx(){
    return we_space_idx;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public String getWe_space_admin_nick(){
    return we_space_admin_nick;
}


public AuthorityType getWe_edit_privacy(){
    return we_edit_privacy;
}


public String getWe_view_data(){
    return we_view_data;
}


public String getWe_space_name(){
    return we_space_name;
}


public void setWe_space_idx(Integer we_space_idx){
    this.we_space_idx = we_space_idx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWe_space_idx"))

.queryParam("we_space_idx",we_space_idx)
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