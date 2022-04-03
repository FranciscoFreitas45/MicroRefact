package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AgentService;
public interface AgentServiceRepository extends JpaRepository<AgentService, String>{


public List<AgentService> findByTemplateidAndQualitystatus(String templateid,String qualitystatus)
;

public Page<AgentService> findByOrgiAndStatus(String orgi,String status,Pageable paramPageable)
;

public List<AgentService> findByAgentnoAndStatusAndOrgi(String agentno,String status,String orgi)
;

public int countByUseridAndOrgiAndStatus(String userid,String orgi,String status)
;

public Page<AgentService> findByOrgi(String orgi,Pageable paramPageable)
;

public AgentService findByIdAndOrgi(String paramString,String orgi)
;

public List<AgentService> findByUseridAndOrgi(String paramString,String orgi)
;

public List<AgentService> findAll(Specification<AgentService> spec)
;

public Page<AgentService> findByOrgiAndSatisfaction(String orgi,boolean satisfaction,Pageable paramPageable)
;

public List<AgentService> findByUseridAndOrgiAndStatus(String userid,String orgi,String status)
;

}