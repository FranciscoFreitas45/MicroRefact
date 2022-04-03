package org.danyuan.application.dbms.tabs.dao;
 import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsTypeInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;
@Repository("sysDbmsTabsTypeInfoDao")
@DynamicUpdate(true)
@DynamicInsert(true)
public interface SysDbmsTabsTypeInfoDao extends BaseDao<SysDbmsTabsTypeInfo>{


}