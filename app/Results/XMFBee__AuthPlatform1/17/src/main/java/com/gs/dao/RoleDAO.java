package com.gs.dao;
 import com.gs.bean.Permission;
import com.gs.bean.Role;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public interface RoleDAO extends BaseDAO<String, Role>{


public Role queryByName(String roleName)
;

public int updateStatus(Role role)
;

public int count(String roleStatus)
;

public List<Role> queryAll(Map paramMap)
;

public List<Role> queryByPager(String roleStatus,Pager pager)
;

}