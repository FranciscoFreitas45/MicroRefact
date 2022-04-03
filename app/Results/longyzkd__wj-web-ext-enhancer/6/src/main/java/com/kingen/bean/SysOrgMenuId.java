package com.kingen.bean;
 import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class SysOrgMenuId {

 private  String menuId;

 private  String orgId;

// Constructors
/**
 * default constructor
 */
public SysOrgMenuId() {
}/**
 * full constructor
 */
public SysOrgMenuId(String menuId, String orgId) {
    this.menuId = menuId;
    this.orgId = orgId;
}
public void setOrgId(String orgId){
    this.orgId = orgId;
}


public void setMenuId(String menuId){
    this.menuId = menuId;
}


@Column(name = "org_id", nullable = false, length = 32)
public String getOrgId(){
    return this.orgId;
}


public int hashCode(){
    int result = 17;
    result = 37 * result + (getMenuId() == null ? 0 : this.getMenuId().hashCode());
    result = 37 * result + (getOrgId() == null ? 0 : this.getOrgId().hashCode());
    return result;
}


public boolean equals(Object other){
    if ((this == other))
        return true;
    if ((other == null))
        return false;
    if (!(other instanceof SysOrgMenuId))
        return false;
    SysOrgMenuId castOther = (SysOrgMenuId) other;
    return ((this.getMenuId() == castOther.getMenuId()) || (this.getMenuId() != null && castOther.getMenuId() != null && this.getMenuId().equals(castOther.getMenuId()))) && ((this.getOrgId() == castOther.getOrgId()) || (this.getOrgId() != null && castOther.getOrgId() != null && this.getOrgId().equals(castOther.getOrgId())));
}


@Column(name = "menu_id", nullable = false, length = 32)
public String getMenuId(){
    return this.menuId;
}


}