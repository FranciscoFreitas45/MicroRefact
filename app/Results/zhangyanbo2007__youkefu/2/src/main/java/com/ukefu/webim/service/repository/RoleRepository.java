package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Role;
public interface RoleRepository extends JpaRepository<Role, String>{


public List<Role> findByOrgiAndOrgid(String orgi,String orgid)
;

public List<Role> findByOrgi(String orgi)
;

public Role findByNameAndOrgi(String paramString,String orgi)
;

public Role findByNameAndOrgiAndOrgid(String paramString,String orgi,String orgid)
;

public Role findByIdAndOrgi(String paramString,String orgi)
;

}