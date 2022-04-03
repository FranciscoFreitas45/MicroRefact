package org.danyuan.application.softm.roles.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_roles_info")
@NamedQuery(name = "SysRolesInfo.findAll", query = "SELECT s FROM SysRolesInfo s")
public class SysRolesInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "role_name")
 private  String roleName;

@Column(name = "department_id")
 private  String departmentId;

@Column(name = "checked", precision = 3)
 private  Boolean checked;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysRolesInfo() {
    super();
}
public String getRoleName(){
    return roleName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public void setChecked(Boolean checked){
    this.checked = checked;
}


public void setDepartmentId(String departmentId){
    this.departmentId = departmentId;
}


public Boolean getChecked(){
    return checked;
}


public String getDepartmentId(){
    return departmentId;
}


}