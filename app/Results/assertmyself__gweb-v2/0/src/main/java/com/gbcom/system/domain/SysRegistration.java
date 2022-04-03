package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseSysRegistration;
public class SysRegistration extends BaseSysRegistration{

 private  long serialVersionUID;

/*[CONSTRUCTOR MARKER BEGIN]*/
public SysRegistration() {
    super();
}/**
 * Constructor for primary key
 */
public SysRegistration(java.lang.Long id) {
    super(id);
}/**
 * Constructor for required fields
 */
public SysRegistration(java.lang.Long id, java.lang.String name, java.lang.String organizationCode, java.lang.String address, java.lang.String contacter, java.lang.String telephone, java.lang.String notifyPhone) {
    super(id, name, organizationCode, address, contacter, telephone, notifyPhone);
}
}