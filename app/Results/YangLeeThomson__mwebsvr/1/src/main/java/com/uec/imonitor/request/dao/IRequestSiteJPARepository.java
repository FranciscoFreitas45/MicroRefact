package com.uec.imonitor.request.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.request.bean.RequestSiteEntity;
public interface IRequestSiteJPARepository extends JpaRepository<RequestSiteEntity, Integer>{


@Query("from RequestSiteEntity a where a.requestId = :requestId")
public List<RequestSiteEntity> findByRequestId(Integer requestId)
;

}