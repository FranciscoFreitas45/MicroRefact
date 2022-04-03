package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.Group;
import org.live.sys.entity.GroupRole;
import org.live.sys.entity.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GroupRoleRepository extends BaseRepository<GroupRole, String>{


@Query(value = "delete from GroupRole gr where gr.role.id=:roleId and gr.group.id=:groupId")
@Modifying
public void deleteGroupRoleByRoleId(String groupId,String roleId)
;

@Query(value = "select gr.role.roleName from GroupRole gr where gr.group.id=:groupId")
public String[] findRoleNamesByGroupId(String groupId)
;

@Query(value = "delete from GroupRole gr where gr.group.id=:groupId")
@Modifying
public void deleteGroupRoleByGroupId(String groupId)
;

@Query(value = "select gr.role from GroupRole gr where gr.group=:group")
public List<Role> findRoleByGroup(Group group)
;

}