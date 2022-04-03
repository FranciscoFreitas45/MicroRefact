package com.gs.service;
 import com.gs.bean.Permission;
import com.gs.bean.Role;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface RoleService extends BaseService<String, Role>{


public List<Role> queryCompManager(String rolestatus)
;

public Role queryByName(String roleName)
;

public List<Role> querySysManager(String rolestatus)
;

public int updateStatus(Role role)
;

public int count(String roleStatus)
;

public List<Role> queryAll(String rolestatus)
;

public List<Role> queryByPager(String roleStatus,Pager pager)
;

}