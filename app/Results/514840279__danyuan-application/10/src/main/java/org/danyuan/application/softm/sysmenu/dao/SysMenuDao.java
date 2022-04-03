package org.danyuan.application.softm.sysmenu.dao;
 import java.util.List;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.softm.sysmenu.po.SysMenuInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository("sysMenuDao")
@DynamicUpdate(true)
@DynamicInsert(true)
public interface SysMenuDao extends BaseDao<SysMenuInfo>{


@Transactional
@Modifying(clearAutomatically = true)
@Query("update SysMenuInfo t set t.homePage=0 where t.homePage=1 or t.homePage is null")
public void updateSysMenuInfoHomePage()
;

@Query("select t from SysMenuInfo t  where t.parentsId =:parentsId  and t.deleteFlag = 0")
public List<SysMenuInfo> getSize(String parentsId)
;

@Query("SELECT DISTINCT t FROM SysMenuInfo t " + "WHERE  t.parentsId = :uuid  order by t.sort ")
public List<SysMenuInfo> findzTreeByUser(String id)
;

@Query("select t from SysMenuInfo t where t.parentsId =?1 order by t.sort")
public List<SysMenuInfo> findAllByParentsIdOrderByF_SortCode(String parentsId)
;

@Transactional
@Modifying(clearAutomatically = true)
@Query("update SysMenuInfo t  set t.parentsId=:parentsId,t.sort=:sort where t.uuid =:uuid")
public void updateSysMenuInfoName(String parentsId,int sort,String uuid)
;

@Override
@Query("select t from SysMenuInfo t  where  t.deleteFlag = 0 order by t.sort")
public List<SysMenuInfo> findAll()
;

@Query("select t from SysMenuInfo t  where t.uuid =:uuid")
public SysMenuInfo findAllByUuid(String uuid)
;

@Query("select t from SysMenuInfo t  where t.uuid =:uuid  and t.deleteFlag = 0")
public SysMenuInfo getParentId(String uuid)
;

}