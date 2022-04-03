package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AgentReport;
public interface AgentReportRepository extends JpaRepository<AgentReport, String>{


public List<AgentReport> findByOrgi(String orgi)
;

public AgentReport findByIdAndOrgi(String id,String orgi)
;

}