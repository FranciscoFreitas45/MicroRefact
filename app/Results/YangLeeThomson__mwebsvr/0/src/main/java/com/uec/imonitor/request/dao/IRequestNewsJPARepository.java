package com.uec.imonitor.request.dao;
 import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;
public interface IRequestNewsJPARepository extends JpaRepository<RequestNewsEntity, Integer>{


@Query("from RequestNewsEntity a where a.webpageCode = :webpageCode")
public RequestNewsEntity findByWebpageCode(String webpageCode)
;

@Query("from RequestNewsEntity a where a.requestId = :requestId")
public List<RequestNewsEntity> findByRequestId(Integer requestId)
;

@Query(value = "select rn.newsId, count(nsa) as reprintCount from RequestNewsEntity rn left join rn.reprintNewsList nsa  where rn.requestId in (:requestIds) and rn.reportDatetime >= :startTime and rn.reportDatetime <= :endTime and rn.isDeleted = 0  GROUP BY rn.newsId")
public Page<Object[]> pageRequestNews(List<Integer> requestIds,Date startTime,Date endTime,Pageable pageable)
;

@Query("from RequestNewsEntity a where a.originalCode like %:queryStr% or a.title like %:queryStr%")
public List<RequestNewsEntity> findByOrigCodeOrTitle(String queryStr)
;

}