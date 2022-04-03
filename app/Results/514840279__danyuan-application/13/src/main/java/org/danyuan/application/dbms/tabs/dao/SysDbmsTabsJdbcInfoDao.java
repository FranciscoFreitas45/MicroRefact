package org.danyuan.application.dbms.tabs.dao;
 import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsJdbcInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;
@Repository("sysDbmsTabsJdbcInfoDao")
@DynamicUpdate(true)
@DynamicInsert(true)
public interface SysDbmsTabsJdbcInfoDao extends BaseDao<SysDbmsTabsJdbcInfo>{


}