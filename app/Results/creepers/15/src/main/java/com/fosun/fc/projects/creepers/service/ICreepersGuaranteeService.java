package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersGuarantee;
public interface ICreepersGuaranteeService extends BaseService{


public List<TCreepersGuarantee> queryAllGuarantee()
;

public void saveTCreepersGuarantee(TCreepersGuarantee entity)
;

public List<TCreepersGuarantee> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}