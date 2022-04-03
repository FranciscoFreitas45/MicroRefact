package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface RoleRepository extends BaseRepository<Role, String>{


public List<Role> findByIdIn(String[] roleIds)
;

@Query(value = " select max(r.serialNo) from Role r")
public Integer getMaxSerialNo()
;

@Query(value = "delete from Role r where r.id=:roleId")
@Modifying
public void deleteById(String roleId)
;

@Query(value = "select distinct gr.role from GroupRole gr,User u where gr.group=u.group and " + "gr.group.isEnable=1 and gr.role.isEnable=1 and u.username=:username")
public List<Role> findRolesByUsername(String username)
;

@Query(value = "from Role r where r.serialNo=:serialNo")
public List<Role> findRoleBySerialNo(int serialNo)
;

public Page<Role> findRoles(Pageable pageable,Role role)
;

}