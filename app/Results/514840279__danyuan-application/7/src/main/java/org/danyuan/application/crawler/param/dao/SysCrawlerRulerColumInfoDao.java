package org.danyuan.application.crawler.param.dao;
 import java.util.List;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.crawler.param.po.SysCrawlerRulerColumInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SysCrawlerRulerColumInfoDao extends BaseDao<SysCrawlerRulerColumInfo>{


@Query("From SysCrawlerRulerColumInfo t Where (t.parentUuid is null or t.parentUuid ='' ) and t.rulerUuid  =:rulerUuid  ")
public List<SysCrawlerRulerColumInfo> findParent(String uuid)
;

}