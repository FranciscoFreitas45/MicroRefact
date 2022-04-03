package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersQueryLog;
public interface ICreepersQueryLogService extends BaseService{


public List<TCreepersQueryLog> queryAllQueryLog()
;

public void saveTCreepersQueryLog(TCreepersQueryLog entity)
;

public List<TCreepersQueryLog> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}