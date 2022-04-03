package com.gs.dao;
 import com.gs.bean.Module;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public interface ModuleDAO extends BaseDAO<String, Module>{


public int countByModuleName(String moduleName,String moduleId)
;

public int updPermissions(String oldModuleId,String newModuleId)
;

public List<Module> queryAll(String moduleStatus)
;

public int updPermission(String permissionId,String moduleId)
;

}