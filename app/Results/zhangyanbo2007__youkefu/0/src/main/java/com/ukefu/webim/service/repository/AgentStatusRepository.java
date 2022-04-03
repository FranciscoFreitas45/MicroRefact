package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AgentStatus;
public interface AgentStatusRepository extends JpaRepository<AgentStatus, String>{


public List<AgentStatus> findByOrgi(String orgi)
;

public List<AgentStatus> findByAgentnoAndOrgi(String agentid,String orgi)
;

public AgentStatus findByIdAndOrgi(String paramString,String orgi)
;

}