package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AgentUser;
public interface AgentUserRepository extends JpaRepository<AgentUser, String>{


public int countByAgentnoAndStatusAndOrgi(String agentno,String status,String orgi)
;

public Page<AgentUser> findByOrgiAndStatus(String orgi,String status,Pageable page)
;

public List<AgentUser> findByAgentnoAndStatusAndOrgi(String agentno,String status,String orgi)
;

public List<AgentUser> findByAgentnoAndOrgi(String agentno,String orgi,Sort sort)
;

public AgentUser findOneByAgentnoAndStatusAndOrgi(String id,String status,String orgi)
;

public AgentUser findByIdAndOrgi(String paramString,String orgi)
;

public List<AgentUser> findByUseridAndOrgi(String userid,String orgi)
;

public Page<AgentUser> findAll(Specification<AgentUser> spec,Pageable pageable)
;

}