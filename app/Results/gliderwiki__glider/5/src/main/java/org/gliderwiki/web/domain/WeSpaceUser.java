package org.gliderwiki.web.domain;
 import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
@Table("WE_SPACE_USER")
public class WeSpaceUser implements Serializable{

 private  long serialVersionUID;

@Column(name = "we_space_idx")
 protected  Integer we_space_idx;

@Column(name = "we_user_idx")
 private  Integer we_user_idx;

@Column(name = "we_insert_permit")
 protected  boolean we_insert_permit;

@Column(name = "we_edit_permit")
 protected  boolean we_edit_permit;

@Column(name = "we_view_permit")
 protected  boolean we_view_permit;

@Column(name = "we_ins_date")
 protected  Date we_ins_date;

public WeSpaceUser() {
}public WeSpaceUser(Integer we_space_idx, String type) {
    this.we_space_idx = we_space_idx;
    setAuthorityType(type);
}public WeSpaceUser(int spaceIdx, int userIdx, String authorityType) {
    this.we_space_idx = spaceIdx;
    this.we_user_idx = userIdx;
    setAuthorityType(authorityType);
    this.we_ins_date = new Date();
}public WeSpaceUser(Integer we_space_idx, Integer we_user_idx, boolean we_insert_permit, boolean we_edit_permit, boolean we_view_permit) {
    this.we_space_idx = we_space_idx;
    this.we_user_idx = we_user_idx;
    this.we_insert_permit = we_insert_permit;
    this.we_edit_permit = we_edit_permit;
    this.we_view_permit = we_view_permit;
    this.we_ins_date = new Date();
}
public void setWe_space_idx(Integer we_space_idx){
    this.we_space_idx = we_space_idx;
}


public Integer getWe_space_idx(){
    return we_space_idx;
}


public Integer getWe_user_idx(){
    return we_user_idx;
}


public boolean isWe_view_permit(){
    return we_view_permit;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public boolean isWe_insert_permit(){
    return we_insert_permit;
}


public void setWe_view_permit(boolean we_view_permit){
    this.we_view_permit = we_view_permit;
}


public void setWe_insert_permit(boolean we_insert_permit){
    this.we_insert_permit = we_insert_permit;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public boolean isWe_edit_permit(){
    return we_edit_permit;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_edit_permit(boolean we_edit_permit){
    this.we_edit_permit = we_edit_permit;
}


public void setAuthorityType(String type){
    if (StringUtils.equals("view", type)) {
        setWe_view_permit(true);
    } else {
        setWe_insert_permit(true);
        setWe_edit_permit(true);
    }
}


}