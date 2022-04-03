package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallMonitor;
public interface CallMonitorRepository extends JpaRepository<CallMonitor, String>{


public List<CallMonitor> findByOrgiAndOrgan(String orgi,String organ)
;

public Long countByOrgiAndStatus(String orgi,String status)
;

public CallMonitor findByOrgiAndAgentno(String orgi,String agentno)
;

public List<CallMonitor> findByOrgiAndStatus(String orgi,String status)
;

public List<CallMonitor> findByOrgi(String orgi)
;

public CallMonitor findByUseridAndOrgi(String userid,String orgi)
;

public List<CallMonitor> findByOrgiAndCode(String orgi,String code)
;

}