package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersAssetHandle;
import com.fosun.fc.projects.creepers.Request.TCreepersAssetHandleRequest;
public class TCreepersAssetHandleRequestImpl implements TCreepersAssetHandleRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTCreepersAssetHandles(List<TCreepersAssetHandle> TCreepersAssetHandles,Long id){
 restTemplate.put("http://12/TCreepersAccountBak/{id}/TCreepersAssetHandle/setTCreepersAssetHandles",TCreepersAssetHandles,id);
 return ;
}


public List<TCreepersAssetHandle> getTCreepersAssetHandles(Long id){
 List<TCreepersAssetHandle> aux = restTemplate.getForObject("http://12/TCreepersAccountBak/{id}/TCreepersAssetHandle/getTCreepersAssetHandles",List<TCreepersAssetHandle>.class,id);
return aux;
}


public TCreepersAssetHandle addTCreepersAssetHandle(TCreepersAssetHandle TCreepersAssetHandle,Long id){
 TCreepersAssetHandle aux = restTemplate.getForObject("http://12/TCreepersAccountBak/{id}/TCreepersAssetHandle/addTCreepersAssetHandle?'TCreepersAssetHandle'=TCreepersAssetHandle&'id'=id',",TCreepersAssetHandle.class,id);
return aux;
}


public TCreepersAssetHandle removeTCreepersAssetHandle(TCreepersAssetHandle TCreepersAssetHandle,Long id){
 TCreepersAssetHandle aux = restTemplate.getForObject("http://12/TCreepersAccountBak/{id}/TCreepersAssetHandle/removeTCreepersAssetHandle?'TCreepersAssetHandle'=TCreepersAssetHandle&'id'=id',",TCreepersAssetHandle.class,id);
return aux;
}


}