package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersHlDetail;
public interface ICreepersHlDetailService extends BaseService{


public List<TCreepersHlDetail> queryAllHlDetail()
;

public void saveTCreepersHlDetail(TCreepersHlDetail entity)
;

public List<TCreepersHlDetail> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}