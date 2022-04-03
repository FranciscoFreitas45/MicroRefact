package com.cg.oms.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.oms.model.Role;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface RoleRepository extends JpaRepository<Role, Long>{

@Query(value = "Select * from role t  where t.role_id = ?1", nativeQuery = true)
public Role getRole(Long roleId);

@Transactional
@Modifying
@Query(value = "update role t set t.role_id = ?1 where t.role_id = ?1", nativeQuery = true)
public void setRole(Long roleId,Role role);

}