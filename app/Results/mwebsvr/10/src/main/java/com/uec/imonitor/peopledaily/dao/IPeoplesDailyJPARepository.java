package com.uec.imonitor.peopledaily.dao;
 import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;
public interface IPeoplesDailyJPARepository extends JpaSpecificationExecutor<PeoplesDailyEntity>, JpaRepository<PeoplesDailyEntity, Integer>{


@Query(value = "select count(*) as count ,channel as channel from news_peopledaily where status = 2 and is_core = 1 and create_datetime > date_sub(NOW(),interval 60 minute) group by channel ", nativeQuery = true)
public List<Object> pushDataChannelHour()
;

@Query(value = "select count(*) as count, channel  as channel from news_peopledaily where create_datetime > date_format(now(), '%Y-%m-%d 00:00:00') group by channel ", nativeQuery = true)
public List<Object> groupByChannelHourAll()
;

@Query("from PeoplesDailyEntity a where a.webpageCode = :webpageCode")
public PeoplesDailyEntity findByWebpageCode(String webpageCode)
;

@Query(value = "select count(*) as count ,channel as channel from news_peopledaily where status = 2 and is_core = 1 and create_datetime > date_format(now(), '%Y-%m-%d 00:00:00') group by channel ", nativeQuery = true)
public List<Object> pushDataChannelHourAll()
;

@Query(value = "select count(*) as count ,org  as org from news_peopledaily where status = 2 and is_core = 1 and create_datetime > date_sub(NOW(),interval 60 minute) group by org ", nativeQuery = true)
public List<Object> pushDataOrgHour()
;

@Query(value = "select count(*) as count, org  as org from news_peopledaily where create_datetime > date_sub(NOW(),interval 60 minute) group by org ", nativeQuery = true)
public List<Object> groupByOrgHour()
;

@Query("from PeoplesDailyEntity a where a.isCore = :isCore and a.status = :status ")
public List<PeoplesDailyEntity> findByIsCoreAndStatus(Integer isCore,Integer status)
;

@Query(value = "select count(*) as count ,org  as org from news_peopledaily where status = 2 and is_core = 1 and create_datetime > date_format(now(), '%Y-%m-%d 00:00:00') group by org ", nativeQuery = true)
public List<Object> pushDataOrgHourAll()
;

@Query(value = "select count(*) as count, org  as org from news_peopledaily where create_datetime > date_format(now(), '%Y-%m-%d 00:00:00') group by org ", nativeQuery = true)
public List<Object> groupByOrgHourAll()
;

@Query(value = "select count(*) as count, channel  as channel from news_peopledaily where create_datetime > date_sub(NOW(),interval 60 minute) group by channel ", nativeQuery = true)
public List<Object> groupByChannelHour()
;

}