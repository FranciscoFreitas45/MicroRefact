package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.ReportModel;
public interface ReportModelRepository extends JpaRepository<ReportModel, String>{


public List<ReportModel> findByOrgiAndReportid(String orgi,String reportid,Sort sort)
;

public ReportModel findById(String id)
;

public List<ReportModel> findByParentidAndOrgi(String parentid,String orgi)
;

public ReportModel findByIdAndOrgi(String id,String orgi)
;

}