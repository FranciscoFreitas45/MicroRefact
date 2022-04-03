package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseSysRolePrivilege;
public class SysRolePrivilege extends BaseSysRolePrivilege{

 private  long serialVersionUID;

/*[CONSTRUCTOR MARKER BEGIN]*/
public SysRolePrivilege() {
    super();
}/**
 * Constructor for primary key
 */
public SysRolePrivilege(java.lang.Long id) {
    super(id);
}/**
 * Constructor for required fields
 */
public SysRolePrivilege(java.lang.Long id, com.gbcom.system.domain.SysPrivilege privilege, com.gbcom.system.domain.SysRole role) {
    super(id, privilege, role);
}
}