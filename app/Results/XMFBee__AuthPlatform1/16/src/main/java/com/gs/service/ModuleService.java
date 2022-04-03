package com.gs.service;
 import com.gs.bean.Module;
import java.util.List;
public interface ModuleService extends BaseService<String, Module>{


public int countByModuleName(String moduleName,String moduleId)
;

public int updPermissions(String oldModuleId,String newModuleId)
;

public int updPermission(String permissionId,String moduleId)
;

}