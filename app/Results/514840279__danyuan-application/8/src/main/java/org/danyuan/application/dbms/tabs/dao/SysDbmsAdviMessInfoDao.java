package org.danyuan.application.dbms.tabs.dao;
 import java.util.List;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsAdviMessInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository("sysDbmsAdviMessInfoDao")
public interface SysDbmsAdviMessInfoDao extends BaseDao<SysDbmsAdviMessInfo>{


@Query("select t from SysDbmsAdviMessInfo t where t.deleteFlag=:deleteFlag order by t.type desc,t.jdbcUuid desc,t.tableName")
public List<SysDbmsAdviMessInfo> findByDeleteFlag(Integer deleteFlag)
;

public void setExecuteSql(String id,String executeSql);

}