package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AgentServiceSummary;
public interface ServiceSummaryRepository extends JpaRepository<AgentServiceSummary, String>{


public Page<AgentServiceSummary> findByOrgiAndAgentserviceid(String orgi,String agentserviceid,Pageable pageable)
;

public List<AgentServiceSummary> findByOrgiAndStatuseventid(String orgi,String statuseventid)
;

public AgentServiceSummary findByStatuseventidAndOrgi(String statuseventid,String orgi)
;

public Page<AgentServiceSummary> findByChannelNotAndOrgi(String string,String orgi,Pageable pageable)
;

public Page<AgentServiceSummary> findByChannelAndOrgi(String string,String orgi,Pageable pageable)
;

public AgentServiceSummary findByIdAndOrgi(String id,String orgi)
;

public Page<AgentServiceSummary> findAll(Specification<AgentServiceSummary> spec,Pageable pageable)
;

public Page<AgentServiceSummary> findByOrgiAndUserid(String orgi,String userid,Pageable pageable)
;

public AgentServiceSummary findByAgentserviceidAndOrgi(String agentserviceid,String orgi)
;

}