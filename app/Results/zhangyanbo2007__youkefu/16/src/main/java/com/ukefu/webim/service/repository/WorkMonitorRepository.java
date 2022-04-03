package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.WorkMonitor;
public interface WorkMonitorRepository extends JpaRepository<WorkMonitor, String>{


public Page<WorkMonitor> findByAgentAndOrgi(String agent,String orgi,Pageable paramPageable)
;

public List<WorkMonitor> findByOrgi(String orgi)
;

public int countByAgentAndDatestrAndStatusAndOrgi(String agent,String datestr,String status,String orgi)
;

public List<WorkMonitor> findByOrgiAndAgentAndDatestrAndFirsttime(String orgi,String agent,String datestr,boolean firsttime)
;

public WorkMonitor findByIdAndOrgi(String id,String orgi)
;

public Page<WorkMonitor> findAll(Specification<WorkMonitor> spec,Pageable pageable)
;

}