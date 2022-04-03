package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersAssetHandle;
public interface TCreepersAssetHandleRequest {

   public void setTCreepersAssetHandles(List<TCreepersAssetHandle> TCreepersAssetHandles,Long id);
   public List<TCreepersAssetHandle> getTCreepersAssetHandles(Long id);
   public TCreepersAssetHandle addTCreepersAssetHandle(TCreepersAssetHandle TCreepersAssetHandle,Long id);
   public TCreepersAssetHandle removeTCreepersAssetHandle(TCreepersAssetHandle TCreepersAssetHandle,Long id);
}