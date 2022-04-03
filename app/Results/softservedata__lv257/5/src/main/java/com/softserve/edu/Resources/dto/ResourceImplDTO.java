package com.softserve.edu.Resources.dto;
 import java.util.Arrays;
import java.util.Map;
public class ResourceImplDTO {

 private  long resourceTypeId;

 private  long addressId;

 private  long[] ownerIds;

 private  Map<String,String> propertiesAndValues;


public long[] getOwnerIds(){
    return ownerIds;
}


public long getAddressId(){
    return addressId;
}


public void setOwnerIds(long[] ownerIds){
    this.ownerIds = ownerIds;
}


public long getResourceTypeId(){
    return resourceTypeId;
}


public void setPropertiesAndValues(Map<String,String> propertiesAndValues){
    this.propertiesAndValues = propertiesAndValues;
}


public void setResourceTypeId(long resourceTypeId){
    this.resourceTypeId = resourceTypeId;
}


@Override
public String toString(){
    return "ResourceImplDTO{" + "resourceTypeId=" + resourceTypeId + ", addressId=" + addressId + ", ownerIds=" + Arrays.toString(ownerIds) + ", propertiesAndValues=" + propertiesAndValues + '}';
}


public void setAddressId(long addressId){
    this.addressId = addressId;
}


public Map<String,String> getPropertiesAndValues(){
    return propertiesAndValues;
}


}