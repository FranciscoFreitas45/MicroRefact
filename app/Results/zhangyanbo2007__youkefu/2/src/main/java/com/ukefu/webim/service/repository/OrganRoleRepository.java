package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.OrganRole;
public interface OrganRoleRepository extends JpaRepository<OrganRole, String>{


public List<OrganRole> findByOrgiAndOrgan(String orgi,Organ organ)
;

public List<OrganRole> findByOrgi(String orgi)
;

}