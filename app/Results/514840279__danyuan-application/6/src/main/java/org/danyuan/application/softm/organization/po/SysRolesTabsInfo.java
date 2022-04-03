package org.danyuan.application.softm.organization.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_roles_tabs_info")
@NamedQuery(name = "SysRolesTabsInfo.findAll", query = "SELECT s FROM SysRolesTabsInfo s")
public class SysRolesTabsInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "tabs_id", columnDefinition = " varchar(36) COMMENT '表id'")
 private  String tabsId;

@Column(name = "role_id", columnDefinition = " varchar(36) COMMENT '角色id'")
 private  String roleId;


public void setRoleId(String roleId){
    this.roleId = roleId;
}


public String getTabsId(){
    return tabsId;
}


public void setTabsId(String tabsId){
    this.tabsId = tabsId;
}


public String getRoleId(){
    return roleId;
}


}