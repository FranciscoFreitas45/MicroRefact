package com.softserve.edu.Resources.DTO;
 import com.softserve.edu.Resources.Constants;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
public class ResourceType {

 private  Long id;

 private  String typeName;

 private  String tableName;

 private  ResourceCategory category;

 private  Set<ConstrainedProperty> properties;

 private  boolean instantiated;

 private  User assigner;

 private  ResourceRequest request;

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
    return request;
}


public String getTableName(){
    return tableName;
}


public Set<ConstrainedProperty> getProperties(){
    return Collections.unmodifiableSet(properties);
}


public ResourceCategory getCategory(){
    return category;
}


public Long getId(){
    return id;
}


public String getTypeName(){
    return typeName;
}


public User getAssigner(){
    return assigner;
}


}