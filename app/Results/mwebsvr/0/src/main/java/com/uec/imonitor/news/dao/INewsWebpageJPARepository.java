package com.uec.imonitor.news.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.news.bean.NewsWebpageEntity;
public interface INewsWebpageJPARepository extends JpaRepository<NewsWebpageEntity, String>{


@Query("from NewsWebpageEntity a where a.newsType = :newsType and isDeleted = 0")
public List<NewsWebpageEntity> findByNewsType(Integer newsType)
;

}