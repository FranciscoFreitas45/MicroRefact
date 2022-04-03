package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersBasic;
public interface ICreepersBasicService extends BaseService{


public List<TCreepersBasic> queryAllBasic()
;

public List<TCreepersBasic> findByName(String name)
;

public void saveTCreepersBasic(TCreepersBasic entity)
;

public List<TCreepersBasic> findByIdNo(String idNo)
;

public List<TCreepersBasic> findByRptNo(String RptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}