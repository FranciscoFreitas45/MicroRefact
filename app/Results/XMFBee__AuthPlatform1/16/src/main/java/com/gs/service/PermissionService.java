package com.gs.service;
 import com.gs.bean.Permission;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import java.util.List;
import java.util.Map;
public interface PermissionService extends BaseService<String, Permission>{


public int updateStatus(List permissionIds,String status)
;

public int count()
;

public int countByPerName(String permissionZhname,String permissionId)
;

public List<Permission> queryAll()
;

public List<Permission> queryByPager(String status,Pager pager)
;

}