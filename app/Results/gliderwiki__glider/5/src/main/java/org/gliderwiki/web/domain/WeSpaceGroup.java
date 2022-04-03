package org.gliderwiki.web.domain;
 import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
@Table("WE_SPACE_GROUP")
public class WeSpaceGroup implements Serializable{

 private  long serialVersionUID;

@Column(name = "we_space_idx")
 protected  Integer we_space_idx;

@Column(name = "we_group_idx")
 private  Integer we_group_idx;

@Column(name = "we_insert_permit")
 protected  boolean we_insert_permit;

@Column(name = "we_edit_permit")
 protected  boolean we_edit_permit;

@Column(name = "we_view_permit")
 protected  boolean we_view_permit;

@Column(name = "we_ins_date")
 protected  Date we_ins_date;

public WeSpaceGroup() {
}public WeSpaceGroup(Integer we_space_idx, String type) {
    this.we_space_idx = we_space_idx;
    setAuthorityType(type);
}public WeSpaceGroup(int spaceIdx, int groupIdx, String authorityType) {
    this.we_space_idx = spaceIdx;
    this.we_group_idx = groupIdx;
    setAuthorityType(authorityType);
    this.we_ins_date = new Date();
}public WeSpaceGroup(Integer we_space_idx, Integer we_group_idx, boolean we_insert_permit, boolean we_edit_permit, boolean we_view_permit) {
    this.we_space_idx = we_space_idx;
    this.we_group_idx = we_group_idx;
    this.we_insert_permit = we_insert_permit;
    this.we_edit_permit = we_edit_permit;
    this.we_view_permit = we_view_permit;
    this.we_ins_date = new Date();
}
public Integer getWe_group_idx(){
    return we_group_idx;
}


public void setWe_space_idx(Integer we_space_idx){
    this.we_space_idx = we_space_idx;
}


public Integer getWe_space_idx(){
    return we_space_idx;
}


public boolean isWe_view_permit(){
    return we_view_permit;
}


public void setWe_group_idx(Integer we_group_idx){
    this.we_group_idx = we_group_idx;
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