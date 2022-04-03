package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallOutRole;
public interface CallOutRoleRepository extends JpaRepository<CallOutRole, String>{


public CallOutRole findByOrgiAndRoleid(String orgi,String roleid)
;

public List<CallOutRole> findByOrgi(String orgi)
;

public CallOutRole findByIdAndOrgi(String id,String orgi)
;

}