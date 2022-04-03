package com.ipe.module.core.repository;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.module.core.entity.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface RoleRepository extends CustomerRepository<Role, String>{


@Modifying
@Query("delete from UserRole t where t.user.id=:userId")
public int delUserRole(String userId)
;

}