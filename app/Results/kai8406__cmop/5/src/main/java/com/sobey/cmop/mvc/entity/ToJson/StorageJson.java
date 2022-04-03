package com.sobey.cmop.mvc.entity.ToJson;
 public class StorageJson {

 private  Integer id;

 private  String identifier;

 private  Integer space;

 private  String storageType;

 private  String controllerAlias;

 private  String volume;

 private  String mountPoint;

 private  String mountComputes;


public void setMountPoint(String mountPoint){
    this.mountPoint = mountPoint;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public void setControllerAlias(String controllerAlias){
    this.controllerAlias = controllerAlias;
}


public Integer getId(){
    return id;
}


public void setVolume(String volume){
    this.volume = volume;
}


public void setSpace(Integer space){
    this.space = space;
}


public String getVolume(){
    return volume;
}


public void setMountComputes(String mountComputes){
    this.mountComputes = mountComputes;
}


public String getMountComputes(){
    return mountComputes;
}


public String getIdentifier(){
    return identifier;
}


public Integer getSpace(){
    return space;
}


public void setId(Integer id){
    this.id = id;
}


public void setStorageType(String storageType){
    this.storageType = storageType;
}


public String getMountPoint(){
    return mountPoint;
}


public String getStorageType(){
    return storageType;
}


public String getControllerAlias(){
    return controllerAlias;
}


}