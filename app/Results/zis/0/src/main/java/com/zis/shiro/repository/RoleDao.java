package com.zis.shiro.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.shiro.bean.Role;
public interface RoleDao extends PagingAndSortingRepository<Role, Integer>{

 final String ADMIN = "adminTest";

	final String NORMAL = "normal";
	final String DELETE = "delete";

@Query(value = "SELECT rt FROM Role rt WHERE rt.roleCode <> '" + ADMIN + "' AND rt.status = '" + NORMAL + "' AND rt.id <> 0 ORDER BY rt.updateTime DESC")
public Page<Role> findAllOrderByUpdateTimeDesc(Pageable page)
;

@Query(value = "SELECT rt FROM Role rt WHERE rt.roleCode <> '" + ADMIN + "' AND rt.status = '" + NORMAL + "' AND rt.id <> 0 ORDER BY rt.updateTime DESC")
public List<Role> findByIdNotEqZeroAll()
;

@Query(value = "FROM Role WHERE id =:id AND roleCode <> '" + ADMIN + "' AND status = '" + NORMAL + "'")
public Role findRoleByRoleId(Integer id)
;

@Query(value = "SELECT rt FROM Role rt WHERE rt.roleCode <> '" + ADMIN + "' AND rt.status = '" + NORMAL + "' AND rt.id <> 0 AND rt.roleName LIKE %:roleName% ORDER BY rt.updateTime DESC")
public Page<Role> findByRoleNameLikeOrderByUpdateTimeDesc(String roleName,Pageable page)
;

@Query(value = "SELECT rt FROM Role rt WHERE rt.roleCode <> '" + ADMIN + "' AND rt.status = '" + NORMAL + "' AND rt.id <> 0 AND rt.roleCode = :roleCode")
public Page<Role> findByRoleCode(String roleCode,Pageable page)
;

@Query(value = "SELECT rt FROM User ut, Role rt WHERE rt.status = '" + NORMAL + "' AND rt.id = ut.roleId AND ut.id = :userId")
public List<Role> findRoleByUserId(Integer userId)
;

}