package com.softserve.edu.Resources.DTO;
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


public void setResourceTypeName(String resourceTypeName){
    this.resourceTypeName = resourceTypeName;
}


public String getResourceTypeName(){
    return resourceTypeName;
}


}