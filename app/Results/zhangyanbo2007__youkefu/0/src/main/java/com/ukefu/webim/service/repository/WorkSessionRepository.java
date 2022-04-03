package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.WorkSession;
public interface WorkSessionRepository extends JpaRepository<WorkSession, String>{


public Page<WorkSession> findByAgentAndOrgi(String agent,String orgi,Pageable paramPageable)
;

public List<WorkSession> findByOrgiAndClientid(String orgi,String clientid)
;

public List<WorkSession> findByOrgi(String orgi)
;

public WorkSession findByIdAndOrgi(String id,String orgi)
;

public Page<WorkSession> findAll(Specification<WorkSession> spec,Pageable pageable)
;

public int countByAgentAndDatestrAndOrgi(String agent,String datestr,String orgi)
;

}