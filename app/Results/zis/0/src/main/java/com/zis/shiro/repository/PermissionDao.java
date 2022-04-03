package com.zis.shiro.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.shiro.bean.Permission;
public interface PermissionDao extends PagingAndSortingRepository<Permission, Integer>{

 final String YES = "yes";
	final String NO = "no";
	final String NORMAL = "normal";
	final String DELETE = "delete";


public List<Permission> findByGroupNameAndIdIn(String gsroupName,List<Integer> ids)
;

@Query(value = "SELECT pt FROM User ut, Role rt , RolePermission rpt , Permission pt " + "WHERE rt.id= ut.roleId AND rt.id = rpt.roleId AND pt.id = rpt.permissionId AND ut.id = :userId" + " AND ut.isDelete = '" + NO + "' AND rt.status = '" + NORMAL + "' AND rpt.status = '" + NORMAL + "'")
public List<Permission> findPermissionByUserId(Integer userId)
;

public List<Permission> findByPermissionCode(String permissionCode)
;

public List<Permission> findByUrl(String url)
;

public List<Permission> findByGroupName(String groupName)
;

public List<Permission> findByPermissionCodeIn(List<String> permissionCodes)
;

}