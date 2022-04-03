package org.danyuan.application.softm.roles.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_user_roles_info")
@NamedQuery(name = "SysUserRolesInfo.findAll", query = "SELECT s FROM SysUserRolesInfo s")
public class SysUserRolesInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "checked")
 private  Boolean checked;

@Column(name = "user_id")
 private  String userId;

@Column(name = "roles_id")
 private  String rolesId;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysUserRolesInfo() {
    super();
}public SysUserRolesInfo(String userId, String rolesId) {
    this.userId = userId;
    this.rolesId = rolesId;
}
public void setRolesId(String rolesId){
    this.rolesId = rolesId;
}


public void setChecked(Boolean checked){
    this.checked = checked;
}


public Boolean getChecked(){
    return checked;
}


public String getRolesId(){
    return rolesId;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}