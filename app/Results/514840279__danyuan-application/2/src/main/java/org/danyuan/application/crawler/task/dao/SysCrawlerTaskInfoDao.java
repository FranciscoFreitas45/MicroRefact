package org.danyuan.application.crawler.task.dao;
 import java.util.List;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.crawler.task.po.SysCrawlerTaskInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface SysCrawlerTaskInfoDao extends BaseDao<SysCrawlerTaskInfo>{


@Query("Select distinct t.taskName From SysCrawlerTaskInfo t ")
public List<String> findTaskName()
;

@Query("Select distinct t.urlType From SysCrawlerTaskInfo t ")
public List<String> findUrlType()
;

}