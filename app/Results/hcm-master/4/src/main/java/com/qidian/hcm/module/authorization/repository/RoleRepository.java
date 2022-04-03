package com.qidian.hcm.module.authorization.repository;
 import com.qidian.hcm.module.authorization.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<Role, Long>{


public Role findByNameAndIdNot(String name,Long id)
;

public Role findByName(String name)
;

}