package com.vino.scaffold.shiro.service;
 import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vino.scaffold.service.base.BaseService;
import com.vino.scaffold.shiro.entity.Role;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.exception.RoleDuplicateException;
public interface RoleService extends BaseService<Role, Long>{


public void saveWithCheckDuplicate(Role role,User user) throws RoleDuplicateException
;

public void clearAllRoleAndResourceConnection(Long roleId)
;

public void connectRoleAndResource(Long roleId,Long resourceIds)
;

public void update(Role role)
;

public Page<Role> findRoleByCondition(Map<String,Object> searchParams,Pageable pageable)
;

public void disconnnectRoleAndResource(Long roledId,Long resourceIds)
;

}