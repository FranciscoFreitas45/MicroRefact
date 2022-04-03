package com.kingen.bean;
 import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "sys_org_menu")
public class SysOrgMenu {

 private  SysOrgMenuId id;

// Constructors
/**
 * default constructor
 */
public SysOrgMenu() {
}/**
 * full constructor
 */
public SysOrgMenu(SysOrgMenuId id) {
    this.id = id;
}
public void setId(SysOrgMenuId id){
    this.id = id;
}


@EmbeddedId
@AttributeOverrides({ @AttributeOverride(name = "menuId", column = @Column(name = "menu_id", nullable = false, length = 32)), @AttributeOverride(name = "orgId", column = @Column(name = "org_id", nullable = false, length = 32)) })
public SysOrgMenuId getId(){
    return this.id;
}


}