package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Report;
public interface ReportRepository extends JpaRepository<Report, String>{


public Page<Report> findByOrgi(String orgi,Pageable paramPageable)
;

public List<Report> findByOrgiAndDicid(String orgi,String dicid)
;

public int countByOrgiAndName(String orgi,String name)
;

public Report findByIdAndOrgi(String id,String orgi)
;

public int countByOrgiAndDicid(String orgi,String dicid)
;

}