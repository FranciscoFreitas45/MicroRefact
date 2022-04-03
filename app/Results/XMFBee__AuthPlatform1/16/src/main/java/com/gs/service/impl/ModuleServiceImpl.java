package com.gs.service.impl;
 import com.gs.bean.Checkin;
import com.gs.bean.Module;
import com.gs.bean.User;
import com.gs.dao.ModuleDAO;
import com.gs.service.ModuleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
@Service
public class ModuleServiceImpl implements ModuleService{

@Resource
 private  ModuleDAO moduleDAO;


public List<Module> queryByPagerDisable(Pager pager){
    return moduleDAO.queryByPagerDisable(pager);
}


@Override
public int countByModuleName(String moduleName,String moduleId){
    return moduleDAO.countByModuleName(moduleName, moduleId);
}


public int batchInsert(List<Module> list){
    return moduleDAO.batchInsert(list);
}


public int batchUpdate(List<Module> list){
    return moduleDAO.batchUpdate(list);
}


public Module query(Module module){
    return moduleDAO.query(module);
}


public int count(User user){
    return moduleDAO.count(user);
}


public int insert(Module module){
    return moduleDAO.insert(module);
}


public int update(Module module){
    return moduleDAO.update(module);
}


public int active(String id){
    return moduleDAO.active(id);
}


@Override
public int updPermissions(String oldModuleId,String newModuleId){
    return moduleDAO.updPermissions(oldModuleId, newModuleId);
}


@Override
public List<Module> queryAll(String status){
    return moduleDAO.queryAll(status);
}


public List<Module> blurredQuery(Pager pager,Module module){
    return null;
}


public int delete(Module module){
    return moduleDAO.delete(module);
}


public int batchDelete(List<Module> list){
    return moduleDAO.batchDelete(list);
}


public List<Module> queryByStatus(String status){
    return moduleDAO.queryAll(status);
}


public int inactive(String id){
    return moduleDAO.inactive(id);
}


public int countByBlurred(Module module){
    return 0;
}


public int deleteById(String id){
    return moduleDAO.deleteById(id);
}


public int countByDisable(User user){
    return moduleDAO.countByDisable(user);
}


public Module queryById(String id){
    return moduleDAO.queryById(id);
}


public List<Module> queryByPager(Pager pager){
    return moduleDAO.queryByPager(pager);
}


@Override
public int updPermission(String permissionId,String moduleId){
    return moduleDAO.updPermission(permissionId, moduleId);
}


}