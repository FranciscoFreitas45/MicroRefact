package com.vino.scaffold.shiro.service;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vino.scaffold.service.base.BaseService;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.exception.UserDuplicateException;
public interface UserService extends BaseService<User, Long>{


public Page<User> findUserByCondition(Map<String,Object> searchParams,Pageable pageable)
;

public void saveWithCheckDuplicate(List<User> users) throws UserDuplicateException
;

public User findByUsername(String username)
;

public void disconnectUserAndRole(Long userId,Long roleIds)
;

public void clearAllUserAndRoleConnection(Long userId)
;

public Set<String> findAllPermissionsByUsername(String username)
;

public void update(User user)
;

public Set<String> findAllRoleNamesByUsername(String username)
;

public void connectUserAndRole(Long userId,Long roleIds)
;

public void changePassword(Long userId,String newPassword)
;

}