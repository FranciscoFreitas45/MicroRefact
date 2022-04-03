package org.danyuan.application.dbms.tabs.dao;
 import java.util.List;
import javax.transaction.Transactional;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository("sysDbmsTabsInfoDao")
@DynamicUpdate(true)
@DynamicInsert(true)
public interface SysDbmsTabsInfoDao extends BaseDao<SysDbmsTabsInfo>{


@Query("select distinct t from SysDbmsTabsInfo t where t.uuid in  (  select c.tabsUuid from SysDbmsTabsColsInfo c where c.userIndex=:userindex and c.deleteFlag='0') and t.deleteFlag ='0'   order by t.tabsOrder")
public List<SysDbmsTabsInfo> findAllByUserIndex(String userindex)
;

@Query("select distinct t from SysDbmsTabsInfo t   where t.typeUuid=:typeUuid and t.deleteFlag ='0'  order by t.tabsOrder")
public List<SysDbmsTabsInfo> findAllByTypeUuid(String typeUuid)
;

@Query("select distinct t from SysDbmsTabsInfo t " + " where t.typeUuid=:typeUuid " + " and t.uuid in  (  " + "  select c.tabsUuid from SysDbmsTabsColsInfo c " + "  where c.userIndex=:userindex " + "  and c.deleteFlag='0' ) and t.deleteFlag ='0'  order by t.tabsOrder")
public List<SysDbmsTabsInfo> findAllByUserIndexAndTypeUuid(String userindex,String typeUuid)
;

public SysDbmsTabsInfo findSysDbmsTabsInfoByUuid(String uuid)
;

@Transactional
@Modifying
@Query(" update SysDbmsTabsInfo t set tabsName =:tabsName,tabsDesc=:tabsDesc,typeUuid=:typeUuid,jdbcUuid=:jdbcUuid,updateTime = CURRENT_TIMESTAMP,updateUser = :username  where uuid =:uuid")
public void change(String tabsName,String tabsDesc,String typeUuid,String jdbcUuid,String uuid,String username)
;

@Query("select distinct t from SysDbmsTabsInfo t   where t.jdbcUuid is not null and t.deleteFlag =0 order by t.updateTime")
public List<SysDbmsTabsInfo> findByAddrUuidIsNotNullAndUpdateTimeGreaterThan()
;

}