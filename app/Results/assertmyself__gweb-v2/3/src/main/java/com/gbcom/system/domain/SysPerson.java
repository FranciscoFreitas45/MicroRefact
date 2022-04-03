package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseSysPerson;
import java.util.Set;
public class SysPerson extends BaseSysPerson{

 private  long serialVersionUID;

/*[CONSTRUCTOR MARKER BEGIN]*/
public SysPerson() {
    super();
}/**
 * Constructor for primary key
 */
public SysPerson(java.lang.Long id) {
    super(id);
}
public SysDept getCompany(){
    SysDept dept = getDept();
    if (dept != null) {
        if (dept.getIsCompany()) {
            return dept;
        } else {
            return getParentCompany(dept);
        }
    }
    return null;
}


public SysDept getDept(){
    Set<SysPersonDept> sysPersonDepts = getSysPersonDepts();
    if (sysPersonDepts.size() > 0) {
        SysPersonDept next = sysPersonDepts.iterator().next();
        return next.getDept();
    }
    return null;
}


public SysPersonDept getPersonDept(){
    Set<SysPersonDept> sysPersonDepts = getSysPersonDepts();
    if (sysPersonDepts.size() > 0) {
        return sysPersonDepts.iterator().next();
    }
    return null;
}


public SysDept getParentCompany(SysDept dept){
    if (dept.getParent() != null) {
        return getParentCompany(dept.getParent());
    } else {
        return dept;
    }
}


}