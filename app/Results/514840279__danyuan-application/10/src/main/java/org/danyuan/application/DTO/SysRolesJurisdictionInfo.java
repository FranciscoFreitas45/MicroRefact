package org.danyuan.application.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
public class SysRolesJurisdictionInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

 private  Boolean checked;

 private  String roleId;

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