package org.danyuan.application.dbms.tabs.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.dbms.tabs.dao.SysDbmsAdviMessInfoDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsAdviMessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysDbmsAdviMessInfoService extends BaseServiceImpl<SysDbmsAdviMessInfo>implements BaseService<SysDbmsAdviMessInfo>{

@Autowired
 private SysDbmsAdviMessInfoDao sysDbmsAdviMessInfoDao;


@Override
public List<SysDbmsAdviMessInfo> findAll(SysDbmsAdviMessInfo info){
    return sysDbmsAdviMessInfoDao.findByDeleteFlag(0);
}


}