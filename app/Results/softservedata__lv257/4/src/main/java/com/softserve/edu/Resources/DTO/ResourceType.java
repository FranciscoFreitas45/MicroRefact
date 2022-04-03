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

 private Long idWR9B;

public ResourceType() {
}public ResourceType(String typeName) {
    this.typeName = typeName;
    properties = new HashSet<>();
}
public ResourceType setTableName(String tableName){
    this.tableName = tableName;
    return this;
}


public ResourceType setRequest(ResourceRequest request){
    this.request = request;
    return this;
}


public String getName(){
    return typeName;
}


public Optional<ConstrainedProperty> getProperty(String propertyName){
    return properties.stream().filter(rp -> rp.getProperty().getTitle().equalsIgnoreCase(propertyName)).findFirst();
}


public boolean isInstantiated(){
    return instantiated;
}


public ResourceRequest getRequest(){
    return request;
}


public void setProperties(Set<ConstrainedProperty> properties){
    this.properties = properties;
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


public void addProperty(ConstrainedProperty constrainedProperty){
    properties.add(constrainedProperty);
}


public ResourceType setCategory(ResourceCategory category){
    this.category = category;
    return this;
}


@Override
public int hashCode(){
    return typeName.hashCode();
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ResourceType that = (ResourceType) o;
    return typeName.equals(that.typeName);
}


public ResourceType setTypeName(String typeName){
    this.typeName = typeName;
    return this;
}


public ResourceType setInstantiated(boolean instantiated){
    this.instantiated = instantiated;
    return this;
}


public ResourceType setAssigner(User assigner){
    this.assigner = assigner;
    return this;
}


public ResourceType setId(Long id){
    this.id = id;
    return this;
}


@Override
public String toString(){
    return "ResourceType [id=" + id + ", typeName=" + typeName + ", tableName=" + tableName + ", category=" + category + ", properties=" + properties + ", instantiated=" + instantiated + ", assigner=" + assigner + "]";
}


public String getTypeName(){
    return typeName;
}


public User getAssigner(){
    return assigner;
}


public void removeProperty(ConstrainedProperty constrainedProperty){
    properties.remove(constrainedProperty);
}


}