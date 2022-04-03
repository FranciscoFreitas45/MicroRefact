package org.live.sys.service;
 import org.live.common.base.BaseService;
import org.live.sys.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface RoleService extends BaseService<Role, String>{


public Integer createSerialNo()
;

public List<Role> findRoleBySerialNo(int serialNo)
;

public boolean isExistSerialNo(int serialNo)
;

public Page<Role> findRoles(Pageable pageable,Role role)
;

}