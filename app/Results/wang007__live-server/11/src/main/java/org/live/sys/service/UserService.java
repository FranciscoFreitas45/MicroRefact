package org.live.sys.service;
 import org.live.common.base.BaseService;
import org.live.sys.entity.Permission;
import org.live.sys.entity.Role;
import org.live.sys.entity.User;
import org.live.sys.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface UserService extends BaseService<User, String>{


public void updateUser4isDelete(String[] ids)
;

public List<Permission> findPermissionsByUsername(String username)
;

public List<User> findByUsername(String username)
;

public void saveUserInfo(UserVo userVo,String password)
;

public void updateUserInfo(UserVo userVo)
;

public boolean isExistUser(String username)
;

public List<Role> findRolesByUsername(String username)
;

public List<String> getRoleValuesByUsername(String username)
;

public Page<UserVo> findUsers(Pageable pageable,UserVo userVo)
;

public List<String> getPermissionValuesByUsername(String username)
;

public Page<UserVo> findUserisDelete(Pageable pageable,UserVo userVo)
;

}