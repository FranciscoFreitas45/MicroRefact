package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.live.Request.PermissionRequest;
import org.live.Request.Impl.PermissionRequestImpl;
import org.live.DTO.Permission;
@Entity
@Table(name = "sys_role_permission")
public class RolePermission extends BaseEntity{

 private  long serialVersionUID;

@ManyToOne
@JoinColumn(name = "role_id")
 private  Role role;

@Transient
 private  Permission permission;

@Column(name = "idJL3E")
 private String idJL3E;

@Transient
 private PermissionRequest permissionrequest = new PermissionRequestImpl();;

public RolePermission() {
}public RolePermission(Role role, Permission permission) {
    this.role = role;
    this.permission = permission;
}
public Permission getPermission(){
  this.permission = permissionrequest.getPermission(this.idJL3E);
return this.permission;
}}



public void setRole(Role role){
    this.role = role;
}


public Role getRole(){
    return role;
}


public void setPermission(Permission permission){
this.idJL3E = permission.getPermission() ;
permissionrequest.setPermission(permission,this.idJL3E);
 this.permission = permission;
}



}