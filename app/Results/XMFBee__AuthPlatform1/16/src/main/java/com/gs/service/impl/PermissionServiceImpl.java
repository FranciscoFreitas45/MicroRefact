package com.gs.service.impl;
 import com.gs.bean.Checkin;
import com.gs.bean.Permission;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.PermissionDAO;
import com.gs.service.PermissionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PermissionServiceImpl implements PermissionService{

@Resource
 private  PermissionDAO permissionDAO;


public List<Permission> queryByPagerDisable(Pager pager){
    return permissionDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<Permission> list){
    return permissionDAO.batchInsert(list);
}


public int batchUpdate(List<Permission> list){
    return permissionDAO.batchUpdate(list);
}


public Permission query(Permission permission){
    return permissionDAO.query(permission);
}


public int count(){
    return count("");
}


public int insert(Permission permission){
    return permissionDAO.insert(permission);
}


public int update(Permission permission){
    return permissionDAO.update(permission);
}


public int active(String id){
    return permissionDAO.active(id);
}


@Override
public List<Permission> queryAll(String status){
    return permissionDAO.queryAll(status);
}


public List<Permission> blurredQuery(Pager pager,Permission permission){
    return permissionDAO.blurredQuery(pager, permission);
}


public int delete(Permission permission){
    return permissionDAO.delete(permission);
}


public int batchDelete(List<Permission> list){
    return permissionDAO.batchDelete(list);
}


public List<Permission> queryByStatus(String status){
    return permissionDAO.queryAll(status);
}


public int inactive(String id){
    return permissionDAO.inactive(id);
}


@Override
public int countByBlurred(Permission permission,User user){
    return permissionDAO.countByBlurred(permission, user);
}


@Override
public int updateStatus(List permissionIds,String status){
    Map map = new HashMap();
    map.put("permissionIds", permissionIds);
    map.put("permissionStatus", status);
    return permissionDAO.updateStatus(map);
}


public int deleteById(String id){
    return permissionDAO.deleteById(id);
}


public int countByDisable(User user){
    return permissionDAO.countByDisable(user);
}


@Override
public int countByPerName(String permissionZhname,String permissionId){
    return permissionDAO.countByPerName(permissionZhname, permissionId);
}


public List<Permission> queryByPager(Pager pager){
    return queryByPager("", pager);
}


public Permission queryById(String id){
    return permissionDAO.queryById(id);
}


}