package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicIsp;
public interface ICreepersPublicIspService extends BaseService{


public void saveTCreepersPublicIsp(TCreepersPublicIsp entity)
;

public List<TCreepersPublicIsp> queryAllPublicIsp()
;

public List<TCreepersPublicIsp> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}