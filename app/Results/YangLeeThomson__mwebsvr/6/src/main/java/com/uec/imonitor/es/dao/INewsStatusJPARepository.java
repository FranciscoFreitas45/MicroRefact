package com.uec.imonitor.es.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.es.bean.NewsStatusEntity;
public interface INewsStatusJPARepository extends JpaRepository<NewsStatusEntity, Integer>{


@Query("from NewsStatusEntity a where a.tableName = :tableName and a.status = :status and a.webpageCode = :webpageCode")
public List<NewsStatusEntity> listByTableNameAndStatusAndWebpageCode(String tableName,Integer status,String webpageCode)
;

@Query(value = "select * from news_status  a where a.table_name = :tableName  order by innerid desc limit :topN", nativeQuery = true)
public List<NewsStatusEntity> listByTableNameTopN(String tableName,int topN)
;

@Query("from NewsStatusEntity a where a.tableName = :tableName and a.status = :status")
public List<NewsStatusEntity> listByTableNameAndStatus(String tableName,Integer status)
;

@Query("from NewsStatusEntity a where a.tableName = :tableName")
public List<NewsStatusEntity> listByTableName(String tableName)
;

@Query(value = "select * from news_status  a where a.table_name = :tableName and a.status in :statusList order by innerid desc limit :topN", nativeQuery = true)
public List<NewsStatusEntity> listByTableNameAndStatusTopN(String tableName,List<Integer> statusList,int topN)
;

}