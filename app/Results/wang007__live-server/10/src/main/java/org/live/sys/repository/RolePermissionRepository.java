package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.RolePermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface RolePermissionRepository extends BaseRepository<RolePermission, String>{


@Query(value = "delete rp from sys_role_permission rp,sys_role r, sys_permission p, sys_menu m where " + "rp.permission_id=p.id and p.id=m.permission_id and rp.role_id=:roleId and m.id=:menuId", nativeQuery = true)
@Modifying
public void deleteRolePermissionByIds(String roleId,String menuId)
;

@Query(value = "delete from RolePermission rp where rp.role.id=:roleId")
@Modifying
public void deleteRolePermissionByRoleId(String roleId)
;

}