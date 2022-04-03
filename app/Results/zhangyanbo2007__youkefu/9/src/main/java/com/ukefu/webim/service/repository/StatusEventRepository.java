package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.StatusEvent;
public interface StatusEventRepository extends JpaRepository<StatusEvent, String>{


public List<StatusEvent> findByDataid(String dataid)
;

public int countByAni(String ani)
;

public Page<StatusEvent> findByRecordAndUseridAndOrgi(boolean record,String userid,String orgi,Pageable page)
;

public Page<StatusEvent> findByCalledAndOrgi(String voicemail,String orgi,Pageable page)
;

public List<StatusEvent> findByTemplateidAndQualitystatus(String templateid,String qualitystatus)
;

public int countByAniOrCalled(String ani,String called)
;

public int countByCalled(String called)
;

public Page<StatusEvent> findByOrgi(String orgi,Pageable page)
;

public Page<StatusEvent> findByServicestatusAndOrgi(String servicestatus,String orgi,Pageable page)
;

public Page<StatusEvent> findByAni(String ani,Pageable page)
;

public Page<StatusEvent> findByMisscallAndOrgi(boolean misscall,String orgi,Pageable page)
;

public List<StatusEvent> findByQualitydisuserAndQualitystatus(String qualitydisuser,String qualitystatus)
;

public int countByAgent(String agent)
;

public StatusEvent findByIdOrBridgeid(String id,String bridgeid)
;

public List<StatusEvent> findAll(Specification<StatusEvent> spec)
;

public List<StatusEvent> findByCode(String ani)
;

public Page<StatusEvent> findByNameid(String nameid,Pageable page)
;

public Page<StatusEvent> findByRecordAndOrgi(boolean record,String orgi,Pageable page)
;

public StatusEvent findById(String id)
;

public StatusEvent findByIdAndOrgi(String id,String orgi)
;

}