package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseSysPrivilege;
import com.hc.core.security.privilege.Privilege;
public class SysPrivilege extends BaseSysPrivilegeimplements Privilege{

 private  long serialVersionUID;

 private  String privilegeType;

/*[CONSTRUCTOR MARKER BEGIN]*/
public SysPrivilege() {
    super();
}/**
 * Constructor for primary key
 */
public SysPrivilege(java.lang.Long id) {
    super(id);
}/**
 * Constructor for required fields
 */
public SysPrivilege(java.lang.Long id, java.lang.String code) {
    super(id, code);
}
public String getPrivilegeType(){
    try {
        if (this.privilegeType == null || this.privilegeType.equals("")) {
            setPrivilegeType();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return this.privilegeType;
}


public void setPrivilegeType(){
    if (this.getType() != null) {
        this.privilegeType = this.getType().getCode().toUpperCase();
    }
}


}