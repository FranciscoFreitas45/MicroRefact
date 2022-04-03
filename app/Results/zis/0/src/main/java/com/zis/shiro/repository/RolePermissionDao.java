package com.zis.shiro.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.shiro.bean.RolePermission;
public interface RolePermissionDao extends PagingAndSortingRepository<RolePermission, Integer>{

    final String NORMAL = "normal";
	final String DELETE = "delete";

@Query(value = "FROM RolePermission WHERE roleId = :roleId AND status = '" + NORMAL + "'")
public List<RolePermission> findByRoleId(Integer roleId)
;

}