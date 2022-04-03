package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.Permission;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface PermissionRepository extends BaseRepository<Permission, String>{


@Query(value = "select distinct rp.permission from RolePermission rp,GroupRole gr,User u " + "where rp.role= gr.role and u.group=gr.group and rp.permission.isEnable=1 " + "and gr.role.isEnable=1 and gr.group.isEnable=1 and u.username=:username")
public List<Permission> findPermissionsByUsername(String username)
;

public Permission getPermission(String idJL3E);

public void setPermission(String idJL3E,Permission permission);

public Permission getPermission(String idIJY1);

public void setPermission(String idIJY1,Permission permission);

}