package com.softserve.edu.Resources.dto;
 import java.util.List;
import java.util.Map;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.Owner;
public class GenericResourceDTO {

 private  long id;

 private  String resourceTypeName;

 private  Map<String,String> resourcePropertyValues;

 private  List<Owner> owners;

 private  Address address;

public GenericResourceDTO() {
// TODO Auto-generated constructor stub
}public GenericResourceDTO(long id, Map<String, String> resourcePropertyValues) {
    this.id = id;
    this.resourcePropertyValues = resourcePropertyValues;
}
public void setOwners(List<Owner> owners){
    this.owners = owners;
}


public void setAddress(Address address){
    this.address = address;
}


public List<Owner> getOwners(){
    return owners;
}


public void setResourceTypeName(String resourceTypeName){
    this.resourceTypeName = resourceTypeName;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public Address getAddress(){
    return address;
}


public Map<String,String> getResourcePropertyValues(){
    return resourcePropertyValues;
}


public void setResourcePropertyValues(Map<String,String> resourcePropertyValues){
    this.resourcePropertyValues = resourcePropertyValues;
}


public String getResourceTypeName(){
    return resourceTypeName;
}


}