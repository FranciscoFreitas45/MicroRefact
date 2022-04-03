package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicCivil;
public interface ICreepersPublicCivilService extends BaseService{


public List<TCreepersPublicCivil> queryAllCompensatory()
;

public void saveCreepersPublicCivilDTO(TCreepersPublicCivil entity)
;

public List<TCreepersPublicCivil> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}