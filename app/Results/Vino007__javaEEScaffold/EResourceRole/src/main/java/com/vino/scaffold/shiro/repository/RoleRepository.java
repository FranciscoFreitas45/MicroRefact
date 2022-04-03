package com.vino.scaffold.shiro.repository;
 import com.vino.scaffold.repository.base.BaseRepository;
import com.vino.scaffold.shiro.entity.Role;
public interface RoleRepository extends BaseRepository<Role, Long>{


public void deleteAssociateById(Long roleIds)
;

public Role findByName(String name)
;

}