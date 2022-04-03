package com.gs.dao;
 import com.gs.bean.Supply;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserRoleDAO extends BaseDAO<String, UserRole>{


public int countByBlurred(UserRole userRole,User user)
;

public List<UserRole> blurredQuery(Pager pager,UserRole userRole)
;

}