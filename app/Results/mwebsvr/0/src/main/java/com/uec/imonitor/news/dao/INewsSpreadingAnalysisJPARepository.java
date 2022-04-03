package com.uec.imonitor.news.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;
public interface INewsSpreadingAnalysisJPARepository extends JpaRepository<NewsSpreadingAnalysisEntity, Integer>{


@Query("from NewsSpreadingAnalysisEntity a where a.newsId = :newsId and isDeleted = 0")
public List<NewsSpreadingAnalysisEntity> findByNewsId(Integer newsId)
;

@Query("from NewsSpreadingAnalysisEntity a where a.webpageCode = :webpageCode")
public List<NewsSpreadingAnalysisEntity> findByWebpageCode(String webpageCode)
;

@Query("from NewsSpreadingAnalysisEntity a where a.newsId = :newsId and a.status = :status and isDeleted = 0")
public List<NewsSpreadingAnalysisEntity> findByNewsIdAndStatus(Integer newsId,Integer status)
;

@Query("from NewsSpreadingAnalysisEntity a where a.newsId = :newsId and a.webpageCode = :webpageCode and isDeleted = 0")
public NewsSpreadingAnalysisEntity findByNewsIdAndCode(Integer newsId,String webpageCode)
;

}