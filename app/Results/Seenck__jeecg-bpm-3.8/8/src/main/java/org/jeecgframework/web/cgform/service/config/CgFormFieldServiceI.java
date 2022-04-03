package org.jeecgframework.web.cgform.service.config;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.core.common.service.CommonService;
public interface CgFormFieldServiceI extends CommonService{


public String getCgFormVersionById(String id)
;

public void saveTable(CgFormHeadEntity cgFormHead,String a)
;

public Map<String,CgFormFieldEntity> getAllCgFormFieldByTableName(String tableName,String version)
;

public boolean updateVersion(String formId)
;

public void deleteCgForm(CgFormHeadEntity cgFormHead)
;

public List<Map<String,Object>> getSubTableData(String mainTableName,String subTableName,Object mainTableId)
;

public void updateTable(CgFormHeadEntity t,String sign,boolean isChange)
;

public List<Map<String,Object>> getPeizhiCountByIds(List<CgFormHeadEntity> list)
;

public int getByphysiceId(String id)
;

public Map<String,Object> getFtlFormConfig(String tableName,String version)
;

public Map<String,CgFormFieldEntity> getCgFormFieldByFormId(String formid)
;

public boolean appendSubTableStr4Main(CgFormHeadEntity entity)
;

public boolean removeSubTableStr4Main(CgFormHeadEntity entity)
;

public String getCgFormVersionByTableName(String tableName)
;

public void sortSubTableStr(CgFormHeadEntity entity)
;

public Boolean judgeTableIsExit(String tableName)
;

public List<Map<String,Object>> getCgFormFieldByTableName(String tableName)
;

public CgFormHeadEntity getCgFormHeadByTableName(String tableName,String version)
;

public boolean checkTableExist(String tableName)
;

public boolean dbSynch(CgFormHeadEntity cgFormHead,String synMethod)
;

public List<CgFormFieldEntity> getHiddenCgFormFieldByTableName(String tableName)
;

}