package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.RoleAuth;
public interface RoleAuthRepository extends JpaRepository<RoleAuth, String>{


public List<RoleAuth> findByRoleidAndOrgi(String roleid,String orgi)
;

public RoleAuth findByNameAndOrgi(String paramString,String orgi)
;

public RoleAuth findByIdAndOrgi(String paramString,String orgi)
;

public List<RoleAuth> findAll(Specification<RoleAuth> spec)
;

}