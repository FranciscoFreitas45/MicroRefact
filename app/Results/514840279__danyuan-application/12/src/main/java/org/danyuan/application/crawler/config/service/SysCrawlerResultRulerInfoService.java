package org.danyuan.application.crawler.config.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.crawler.config.dao.SysCrawlerResultRulerInfoDao;
import org.danyuan.application.crawler.config.po.SysCrawlerResultRulerInfo;
import org.danyuan.application.crawler.config.vo.SysCrawlerResultRulerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysCrawlerResultRulerInfoDao;
@Service
public class SysCrawlerResultRulerInfoService extends BaseServiceImpl<SysCrawlerResultRulerInfo>implements BaseService<SysCrawlerResultRulerInfo>{

@Autowired
 private SysCrawlerResultRulerInfoDao sysCrawlerResultRulerInfoDao;


public List<SysCrawlerResultRulerInfo> saveResultRulerInfo(SysCrawlerResultRulerVo vo){
    SysCrawlerResultRulerInfo info = new SysCrawlerResultRulerInfo();
    info.setTableUuid(vo.getTableUuid());
    info.setRulerUuid(vo.getRulerUuid());
    Example<SysCrawlerResultRulerInfo> example = Example.of(info);
    sysCrawlerResultRulerInfoDao.deleteAll(sysCrawlerResultRulerInfoDao.findAll(example));
    if (vo.getList() != null) {
        for (SysCrawlerResultRulerInfo iterable_element : vo.getList()) {
            iterable_element.setTableUuid(vo.getTableUuid());
            iterable_element.setRulerUuid(vo.getRulerUuid());
            sysCrawlerResultRulerInfoDao.save(iterable_element);
        }
    }
    return sysCrawlerResultRulerInfoDao.findAll(example);
}


}