package com.softserve.edu.Resources.dto;
 public class GroupedResourceCount {

 private  String resourceTypeName;

 private  long resourceTypeId;

 private  long resourceRecordsCount;

public GroupedResourceCount(String resourceTypeName, long resourceTypeId, long resourceRecordsCount) {
    super();
    this.resourceTypeName = resourceTypeName;
    this.resourceTypeId = resourceTypeId;
    this.resourceRecordsCount = resourceRecordsCount;
}
public long getResourceTypeId(){
    return resourceTypeId;
}


public long getResourceRecordsCount(){
    return resourceRecordsCount;
}


public void setResourceTypeId(long resourceTypeId){
    this.resourceTypeId = resourceTypeId;
}


public void setResourceTypeName(String resourceTypeName){
    this.resourceTypeName = resourceTypeName;
}


public void setResourceRecordsCount(long resourceRecordsCount){
    this.resourceRecordsCount = resourceRecordsCount;
}


public String getResourceTypeName(){
    return resourceTypeName;
}


}