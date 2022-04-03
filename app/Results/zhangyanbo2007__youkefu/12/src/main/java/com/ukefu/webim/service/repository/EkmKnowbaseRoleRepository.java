package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmKnowbaseRole;
public interface EkmKnowbaseRoleRepository extends JpaRepository<EkmKnowbaseRole, String>{


public EkmKnowbaseRole findByRoleidAndKnowbaseidAndOrgi(String roleid,String knowbaseid,String orgi)
;

public List<EkmKnowbaseRole> findByKnowbaseidAndOrgi(String knowbaseid,String orgi)
;

public List<EkmKnowbaseRole> findByRoleidAndOrgi(String roleid,String orgi)
;

public EkmKnowbaseRole findByIdAndOrgi(String id,String orgi)
;

}