package com.softserve.edu.Resources.DTO;
 import com.softserve.edu.Resources.Constants;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import com.softserve.edu.Resources.Request.ResourceCategoryRequest;
import com.softserve.edu.Resources.Request.Impl.ResourceCategoryRequestImpl;
import com.softserve.edu.Resources.DTO.ResourceCategory;
import com.softserve.edu.Resources.Request.UserRequest;
import com.softserve.edu.Resources.Request.Impl.UserRequestImpl;
import com.softserve.edu.Resources.DTO.User;
import com.softserve.edu.Resources.Request.ResourceRequestRequest;
import com.softserve.edu.Resources.Request.Impl.ResourceRequestRequestImpl;
import com.softserve.edu.Resources.DTO.ResourceRequest;
public class ResourceType {

 private  Long id;

 private  String typeName;

 private  String tableName;

 private  ResourceCategory category;

 private  Set<ConstrainedProperty> properties;

 private  boolean instantiated;

 private  User assigner;

 private  ResourceRequest request;

 private Long idWR9B;

 private Long id93GK;

 private long idW6RM;

public ResourceType() {
}public ResourceType(String typeName) {
    this.typeName = typeName;
    properties = new HashSet<>();
}
public String getName(){
    return typeName;
}


public Optional<ConstrainedProperty> getProperty(String propertyName){
    return properties.stream().filter(rp -> rp.getProperty().getTitle().equalsIgnoreCase(propertyName)).findFirst();
}


public ResourceRequest getRequest(){
  this.request = resourcerequestrequest.getRequest(this.idW6RM);
return this.request;
}}



public String getTableName(){
    return tableName;
}


public Set<ConstrainedProperty> getProperties(){
    return Collections.unmodifiableSet(properties);
}


public ResourceCategory getCategory(){
  this.category = resourcecategoryrequest.getCategory(this.idWR9B);
return this.category;
}}



public Long getId(){
    return id;
}


public String getTypeName(){
    return typeName;
}


public User getAssigner(){
  this.assigner = userrequest.getAssigner(this.id93GK);
return this.assigner;
}}



}