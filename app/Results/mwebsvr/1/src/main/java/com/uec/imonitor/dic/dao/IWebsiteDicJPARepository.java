package com.uec.imonitor.dic.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.dic.bean.WebsiteDicEntity;
public interface IWebsiteDicJPARepository extends JpaRepository<WebsiteDicEntity, Integer>{


@Query("from WebsiteDicEntity a where a.parentId = :parentId and a.status = :status ")
public List<WebsiteDicEntity> findByParentId(Integer parentId,Integer status)
;

@Query("from WebsiteDicEntity a where a.parentId != 0 and a.name = :name and a.status = :status ")
public WebsiteDicEntity findWebsiteByName(String name,Integer status)
;

@Query("from WebsiteDicEntity a where a.parentId != 0 and a.status = :status ")
public List<WebsiteDicEntity> findAllWebsite(Integer status)
;

}