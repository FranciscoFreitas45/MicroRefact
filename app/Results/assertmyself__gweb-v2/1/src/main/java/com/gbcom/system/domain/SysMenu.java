package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseSysMenu;
public class SysMenu extends BaseSysMenu{

 private  long serialVersionUID;

/*[CONSTRUCTOR MARKER BEGIN]*/
public SysMenu() {
    super();
}/**
 * Constructor for primary key
 */
public SysMenu(java.lang.Long id) {
    super(id);
}/**
 * Constructor for required fields
 */
public SysMenu(java.lang.Long id, java.lang.String name, java.lang.String privilege) {
    super(id, name, privilege);
}
}