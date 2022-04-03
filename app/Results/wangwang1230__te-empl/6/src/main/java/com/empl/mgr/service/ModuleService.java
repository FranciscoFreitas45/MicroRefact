package com.empl.mgr.service;
 import com.empl.mgr.constant.MethodType;
import com.empl.mgr.support.JSONReturn;
public interface ModuleService {


public JSONReturn findBreadcrumb(String moduleCode)
;

public JSONReturn findAllModule(long roleId)
;

public JSONReturn setRoleSecureValid(long rold,String code,int type,boolean add)
;

public JSONReturn findMenu(String acctName)
;

public boolean secureValid(String userName,String[] code,MethodType type)
;

public JSONReturn findModuleParameter(String moduleCode,String acctName)
;

}