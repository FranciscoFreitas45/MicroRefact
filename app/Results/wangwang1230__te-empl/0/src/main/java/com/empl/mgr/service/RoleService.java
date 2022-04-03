package com.empl.mgr.service;
 import com.empl.mgr.support.JSONReturn;
public interface RoleService {


public JSONReturn modifyRole(long id,String roleName,String desc,String acctName)
;

public JSONReturn findRoleById(long id)
;

public JSONReturn findRolePage(int page,String searchVal)
;

public JSONReturn deleteRole(long id,String acctName)
;

public JSONReturn addRole(String roleName,String desc,String acctName)
;

public JSONReturn findRoleList(int page,String searchVal,String acctName)
;

}