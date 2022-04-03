package org.danyuan.application.dbms.tabs.dao;
 import javax.transaction.Transactional;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsColsInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository("sysDbmsTabsColsInfoDao")
@DynamicUpdate(true)
@DynamicInsert(true)
public interface SysDbmsTabsColsInfoDao extends BaseDao<SysDbmsTabsColsInfo>{


@Transactional
@Modifying
@Query("delete from SysDbmsTabsColsInfo where tabsUuid=:tabsUuid")
public void deleteByTabsUuid(String string)
;

public void setTabsUuid(String id,String tabsUuid);

}