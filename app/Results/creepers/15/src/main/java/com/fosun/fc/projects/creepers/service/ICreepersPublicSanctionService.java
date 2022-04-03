package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicSanction;
public interface ICreepersPublicSanctionService extends BaseService{


public List<TCreepersPublicSanction> queryAllPublicSanction()
;

public void saveTCreepersPublicSanction(TCreepersPublicSanction entity)
;

public List<TCreepersPublicSanction> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}