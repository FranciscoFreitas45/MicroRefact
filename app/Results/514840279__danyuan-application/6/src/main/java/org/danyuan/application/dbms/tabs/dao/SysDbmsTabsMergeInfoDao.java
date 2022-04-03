package org.danyuan.application.dbms.tabs.dao;
 import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsMergeInfo;
import org.springframework.stereotype.Repository;
@Repository
public interface SysDbmsTabsMergeInfoDao extends BaseDao<SysDbmsTabsMergeInfo>{


public void setTableUuid1(String id,String tableUuid1);

public void setColsName1(String id,String colsName1);

public void setColsDesc1(String id,String colsDesc1);

public void setColsUuid1(String id,String colsUuid1);

public void setTableUuid2(String id,String tableUuid2);

public void setColsName2(String id,String colsName2);

public void setColsDesc2(String id,String colsDesc2);

public void setColsUuid2(String id,String colsUuid2);

}