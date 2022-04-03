package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StorageItemController {

 private StorageItem storageitem;

 private StorageItem storageitem;


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
storageitem.setId(id);
}


@PutMapping
("/setApply")
public void setApply(@RequestParam(name = "apply") Apply apply){
storageitem.setApply(apply);
}


@PutMapping
("/setIdentifier")
public void setIdentifier(@RequestParam(name = "identifier") String identifier){
storageitem.setIdentifier(identifier);
}


@PutMapping
("/setSpace")
public void setSpace(@RequestParam(name = "space") Integer space){
storageitem.setSpace(space);
}


@PutMapping
("/setStorageType")
public void setStorageType(@RequestParam(name = "storageType") Integer storageType){
storageitem.setStorageType(storageType);
}


@PutMapping
("/setControllerAlias")
public void setControllerAlias(@RequestParam(name = "controllerAlias") String controllerAlias){
storageitem.setControllerAlias(controllerAlias);
}


@PutMapping
("/setVolume")
public void setVolume(@RequestParam(name = "volume") String volume){
storageitem.setVolume(volume);
}


@PutMapping
("/setMountPoint")
public void setMountPoint(@RequestParam(name = "mountPoint") String mountPoint){
storageitem.setMountPoint(mountPoint);
}


@GetMapping
("/extractToString")
public String extractToString(@RequestParam(name = "computeItems") List<ComputeItem> computeItems){
  return storageitem.extractToString(computeItems);
}


@GetMapping
("/toString")
public String toString(){
  return storageitem.toString();
}


}