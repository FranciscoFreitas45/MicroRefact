package org.danyuan.application.crawler.task.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.crawler.task.dao.SysCrawlerTaskInfoDao;
import org.danyuan.application.crawler.task.po.SysCrawlerTaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysCrawlerTaskInfoService extends BaseServiceImpl<SysCrawlerTaskInfo>implements BaseService<SysCrawlerTaskInfo>{

@Autowired
 private SysCrawlerTaskInfoDao sysCrawlerTaskInfoDao;


public List<String> findTaskName(){
    return sysCrawlerTaskInfoDao.findTaskName();
}


public List<String> findUrlType(){
    return sysCrawlerTaskInfoDao.findUrlType();
}


}