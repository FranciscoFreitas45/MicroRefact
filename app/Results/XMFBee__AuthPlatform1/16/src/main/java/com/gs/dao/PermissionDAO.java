package com.gs.dao;
 import com.gs.bean.Permission;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public interface PermissionDAO extends BaseDAO<String, Permission>{


public int countByBlurred(Permission permission,User user)
;

public int updateStatus(Map paramsMap)
;

public int count(Map paramsMap)
;

public int countByPerName(String permissionZhname,String permissionId)
;

public List<Permission> queryAll()
;

public List<Permission> blurredQuery(Pager pager,Permission checkin)
;

public List<Permission> queryByPager(Map paramsMap)
;

}