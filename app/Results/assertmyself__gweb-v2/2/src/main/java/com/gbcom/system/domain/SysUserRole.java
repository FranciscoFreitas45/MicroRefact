package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseSysUserRole;
public class SysUserRole extends BaseSysUserRole{

 private  long serialVersionUID;

/*[CONSTRUCTOR MARKER BEGIN]*/
public SysUserRole() {
    super();
}/**
 * Constructor for primary key
 */
public SysUserRole(java.lang.Long id) {
    super(id);
}/**
 * Constructor for required fields
 */
public SysUserRole(java.lang.Long id, com.gbcom.system.domain.SysRole role, com.gbcom.system.domain.SysUser user) {
    super(id, role, user);
}
}