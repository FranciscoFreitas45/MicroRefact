package com.weflors.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.weflors.entity.RoleEntity;
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{


@Query("select role.roleName from RoleEntity role where role.roleId = :roleId")
public String getRoleNames(Integer roleId)
;

}