package com.gbcom.system.manager;
 import com.gbcom.system.domain.SysDept;
import org.springframework.stereotype.Service;
@Service
public class SysDeptManager {


public SysDept getSysUnitBySysDept(SysDept dept){
    SysDept company = null;
    if (dept != null) {
        company = getParentCompany(dept);
    }
    return company;
}


public SysDept getParentCompany(SysDept dept){
    if (dept.getParent() != null) {
        return getParentCompany(dept.getParent());
    } else {
        return dept;
    }
}


}