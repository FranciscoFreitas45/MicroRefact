package org.danyuan.application.dbms.tabs.dao;
 import java.util.List;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsUserIndexInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository("sysDbmsUserIndexInfoDao")
@DynamicUpdate(true)
@DynamicInsert(true)
public interface SysDbmsUserIndexInfoDao extends BaseDao<SysDbmsUserIndexInfo>{


@Query("select t from SysDbmsUserIndexInfo t where t.deleteFlag = 0 order by userOrder ")
public List<SysDbmsUserIndexInfo> findAllByDeleteFlag()
;

@Query("select t from SysDbmsUserIndexInfo t where t.chart >0  order by userOrder ")
public List<SysDbmsUserIndexInfo> findAllByChart()
;

}