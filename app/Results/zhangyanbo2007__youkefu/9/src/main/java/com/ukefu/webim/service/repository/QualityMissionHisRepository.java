package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QualityMissionHis;
public interface QualityMissionHisRepository extends JpaRepository<QualityMissionHis, String>{


public List<QualityMissionHis> findByFormfilteridAndOrgi(String formfilterid,String orgi)
;

public List<QualityMissionHis> findByOrgiAndQualitydisuser(String orgi,String qualitydisuser)
;

public List<QualityMissionHis> findByOrgi(String orgi)
;

public List<QualityMissionHis> findByTaskidAndOrgi(String taskid,String orgi)
;

public List<QualityMissionHis> findByDataidAndOrgi(String dataid,String orgi)
;

public List<QualityMissionHis> findByActidAndOrgi(String actid,String orgi)
;

public List<QualityMissionHis> findByQualitypassAndOrgi(String qualitypass,String orgi)
;

public List<QualityMissionHis> findByFilteridAndOrgi(String filterid,String orgi)
;

public Page<QualityMissionHis> findAll(Specification<QualityMissionHis> spec,Pageable pageable)
;

}