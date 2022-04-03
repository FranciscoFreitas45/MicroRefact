package com.ipe.module.core.repository;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.module.core.entity.Authority;
import com.ipe.module.core.entity.Resource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Set;
public interface AuthorityRepository extends CustomerRepository<Authority, String>{


@Query("select t.resource from Authority t where t.role.id=:roleId")
public Set<Resource> getRoleByAuthority(String roleId)
;

@Modifying
@Query("delete from Authority t where t.role.id=:roleId")
public int delRoleAuthority(String roleId)
;

}