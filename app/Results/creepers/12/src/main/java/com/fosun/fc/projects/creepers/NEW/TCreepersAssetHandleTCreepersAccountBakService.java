package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersAssetHandleDao;
import com.fosun.fc.projects.creepers.entity.TCreepersAssetHandle;
@Service
public class TCreepersAssetHandleTCreepersAccountBakService {

@Autowired
 private CreepersAssetHandleDao creepersassethandledao;


public void setTCreepersAssetHandles(Long id,List<TCreepersAssetHandle> TCreepersAssetHandles){
creepersassethandledao.setTCreepersAssetHandles(id,TCreepersAssetHandles);
}


public List<TCreepersAssetHandle> getTCreepersAssetHandles(Long id){
return creepersassethandledao.getTCreepersAssetHandles(id);
}


public TCreepersAssetHandle addTCreepersAssetHandle(Long id,TCreepersAssetHandle TCreepersAssetHandle){
return creepersassethandledao.addTCreepersAssetHandle(id,TCreepersAssetHandle);
}


public TCreepersAssetHandle removeTCreepersAssetHandle(Long id,TCreepersAssetHandle TCreepersAssetHandle){
return creepersassethandledao.removeTCreepersAssetHandle(id,TCreepersAssetHandle);
}


}