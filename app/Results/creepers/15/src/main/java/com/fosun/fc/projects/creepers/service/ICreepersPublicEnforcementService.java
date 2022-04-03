package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicEnforcement;
public interface ICreepersPublicEnforcementService extends BaseService{


public List<TCreepersPublicEnforcement> queryAllPublicEnforcement()
;

public List<TCreepersPublicEnforcement> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

public void saveTCreepersPublicEnforcement(TCreepersPublicEnforcement entity)
;

}