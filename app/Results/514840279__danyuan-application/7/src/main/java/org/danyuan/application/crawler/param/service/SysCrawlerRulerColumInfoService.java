package org.danyuan.application.crawler.param.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.crawler.param.dao.SysCrawlerRulerColumInfoDao;
import org.danyuan.application.crawler.param.po.SysCrawlerRulerColumInfo;
import org.danyuan.application.crawler.param.po.SysCrawlerRulerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysCrawlerRulerColumInfoService extends BaseServiceImpl<SysCrawlerRulerColumInfo>implements BaseService<SysCrawlerRulerColumInfo>{

@Autowired
 private SysCrawlerRulerColumInfoDao sysCrawlerRulerColumInfoDao;


public List<SysCrawlerRulerColumInfo> findParent(SysCrawlerRulerInfo sysCrawlerRulerInfo){
    return sysCrawlerRulerColumInfoDao.findParent(sysCrawlerRulerInfo.getUuid());
}


}