package com.softserve.edu.Resources.dto;
 import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import java.util.HashSet;
import java.util.Set;
public class TypeInfoDTO {

 private  String categoryName;

 private  String typeName;

 private  boolean instantiated;

 private  Set<ConstrainedProperty> properties;

public TypeInfoDTO() {
}public TypeInfoDTO(ResourceType type) {
    this.categoryName = type.getCategory().getCategoryName();
    this.typeName = type.getTypeName();
    this.instantiated = type.isInstantiated();
    this.properties = type.getProperties();
}
public boolean isInstantiated(){
    return instantiated;
}


public Set<ConstrainedProperty> getProperties(){
    return properties;
}


public TypeInfoDTO setProperties(Set<ConstrainedProperty> properties){
    this.properties = properties;
    return this;
}


public String getCategoryName(){
    return categoryName;
}


public TypeInfoDTO setTypeName(String typeName){
    this.typeName = typeName;
    return this;
}


public TypeInfoDTO setInstantiated(boolean instantiated){
    this.instantiated = instantiated;
    return this;
}


public TypeInfoDTO setCategoryName(String categoryName){
    this.categoryName = categoryName;
    return this;
}


public String getTypeName(){
    return typeName;
}


}