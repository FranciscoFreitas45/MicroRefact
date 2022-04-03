package com.gs.service;
 import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface UserRoleService extends BaseService<String, UserRole>{


public int countByBlurred(UserRole userRole,User user)
;

public List<UserRole> blurredQuery(Pager pager,UserRole userRole)
;

}