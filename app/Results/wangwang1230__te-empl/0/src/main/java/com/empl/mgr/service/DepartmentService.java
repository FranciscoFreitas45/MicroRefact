package com.empl.mgr.service;
 import com.empl.mgr.support.JSONReturn;
public interface DepartmentService {


public JSONReturn findDepartmentById(long deptId)
;

public JSONReturn modifyDepartment(long deptId,String name,String desc,String acctName)
;

public JSONReturn addDepartment(String name,String desc,String acctName)
;

public JSONReturn findDeptEmplList(long deptId,String acctName)
;

public JSONReturn setPrincipal(long deptId,long emplId,String acctName)
;

public JSONReturn findAllDepartment()
;

public JSONReturn deleteDepartment(long deptId,String acctName)
;

public JSONReturn findDepartmentList(int page,String searchValue,String acctName)
;

public JSONReturn findDepartmentCount(int page,String searchValue)
;

}