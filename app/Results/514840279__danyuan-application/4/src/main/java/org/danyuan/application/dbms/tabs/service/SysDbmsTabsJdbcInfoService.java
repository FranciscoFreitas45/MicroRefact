package org.danyuan.application.dbms.tabs.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsJdbcInfoDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsJdbcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysDbmsTabsJdbcInfoDao;
@Service("sysDbmsTabsJdbcInfoService")
public class SysDbmsTabsJdbcInfoService extends BaseServiceImpl<SysDbmsTabsJdbcInfo>implements BaseService<SysDbmsTabsJdbcInfo>{

@Autowired
 private  SysDbmsTabsJdbcInfoDao sysDbmsTabsJdbcInfoDao;


public List<SysDbmsTabsJdbcInfo> findAll(){
    return sysDbmsTabsJdbcInfoDao.findAll();
}


}