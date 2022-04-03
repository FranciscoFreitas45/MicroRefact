package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersOlDetail;
public interface ICreepersOlDetailService extends BaseService{


public List<TCreepersOlDetail> queryAllOlDetail()
;

public void saveTCreepersOlDetail(TCreepersOlDetail entity)
;

public List<TCreepersOlDetail> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}