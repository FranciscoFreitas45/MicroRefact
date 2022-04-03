package org.gliderwiki.web.domain;
 import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_GROUP_USER")
@DataTransferObject
public class WeGroupUser extends BaseObjectBean{

@Column(name = "we_group_idx")
 private  String we_group_idx;

@Column(name = "we_user_idx")
 private  String we_user_idx;

@Column(name = "we_use_yn")
 private  String we_use_yn;

@Column(name = "we_ins_user")
 private  Integer we_ins_user;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

public WeGroupUser() {
}public WeGroupUser(Integer weGroupIdx, Integer weUserIdx) {
    this.setWe_group_idx(1 + "");
    this.setWe_ins_date(new Date());
    this.setWe_ins_user(weUserIdx);
    this.setWe_use_yn("Y");
    this.setWe_user_idx(weUserIdx + "");
}
public String getWe_group_idx(){
    return we_group_idx;
}


public String getWe_user_idx(){
    return we_user_idx;
}


public void setWe_user_idx(String we_user_idx){
    this.we_user_idx = we_user_idx;
}


public void setWe_group_idx(String we_group_idx){
    this.we_group_idx = we_group_idx;
}


public String getWe_use_yn(){
    return we_use_yn;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_ins_user(Integer we_ins_user){
    this.we_ins_user = we_ins_user;
}


public Integer getWe_ins_user(){
    return we_ins_user;
}


public void setWe_use_yn(String we_use_yn){
    this.we_use_yn = we_use_yn;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


}