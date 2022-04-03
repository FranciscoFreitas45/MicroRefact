package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.PublishedReport;
public interface PublishedReportRepository extends JpaRepository<PublishedReport, String>{


public List<PublishedReport> findByCode(String code)
;

public Page<PublishedReport> findByOrgi(String orgi,Pageable pageRequest)
;

public Page<PublishedReport> findByOrgiAndDicid(String orgi,String dicid,Pageable pageRequest)
;

public PublishedReport findById(String id)
;

public List<PublishedReport> findByOrgiAndDataidOrderByDataversionDesc(String orgi,String dataid)
;

}