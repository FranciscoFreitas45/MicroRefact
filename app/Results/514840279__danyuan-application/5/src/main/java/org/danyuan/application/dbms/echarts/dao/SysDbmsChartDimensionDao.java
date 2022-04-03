package org.danyuan.application.dbms.echarts.dao;
 import java.util.List;
import javax.transaction.Transactional;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.dbms.echarts.po.SysDbmsChartDimension;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SysDbmsChartDimensionDao extends BaseDao<SysDbmsChartDimension>{


@Transactional
@Modifying
@Query("update SysDbmsChartDimension t set t.groupUuid=:groupUuid where uuid=:uuid")
public void changeGroup(String uuid,String groupUuid)
;

@Query("select t from SysDbmsChartDimension t where t.groupUuid is null or t.groupUuid ='0' order by t.dimeOrder asc")
public List<SysDbmsChartDimension> findAllDime()
;

}