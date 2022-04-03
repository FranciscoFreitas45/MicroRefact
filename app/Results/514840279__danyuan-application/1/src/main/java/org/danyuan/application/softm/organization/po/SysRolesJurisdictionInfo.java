package org.danyuan.application.softm.organization.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_roles_jurisdiction_info")
@NamedQuery(name = "SysRolesJurisdictionInfo.findAll", query = "SELECT s FROM SysRolesJurisdictionInfo s")
public class SysRolesJurisdictionInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "checked")
 private  Boolean checked;

@Column(name = "role_id")
 private  String roleId;

@Column(name = "menu_id")
 private  String menuId;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysRolesJurisdictionInfo() {
    super();
}public SysRolesJurisdictionInfo(String menuId, String roleUuid) {
    this.menuId = menuId;
    this.roleId = roleUuid;
}
public void setRoleId(String roleId){
    this.roleId = roleId;
}


public void setMenuId(String menuId){
    this.menuId = menuId;
}


public void setChecked(Boolean checked){
    this.checked = checked;
}


public String getRoleId(){
    return roleId;
}


public String getMenuId(){
    return menuId;
}


public Boolean getChecked(){
    return checked;
}


}