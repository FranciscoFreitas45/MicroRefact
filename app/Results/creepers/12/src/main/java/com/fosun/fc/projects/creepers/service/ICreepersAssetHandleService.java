package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersAssetHandle;
public interface ICreepersAssetHandleService extends BaseService{


public void saveTCreepersAssetHandle(TCreepersAssetHandle entity)
;

public List<TCreepersAssetHandle> queryAllAssetHandle()
;

public List<TCreepersAssetHandle> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}