package com.softserve.edu.Resources.dto;
 import com.softserve.edu.Resources.entity.ResourceType;
public class ViewTypesDTO {

 private  Long typeId;

 private  String categoryName;

 private  String typeName;

 private  String administratorName;

 private  boolean instantiated;

public ViewTypesDTO() {
}public ViewTypesDTO(ResourceType type) {
    this.typeId = type.getId();
    this.categoryName = type.getCategory().getCategoryName();
    this.typeName = type.getTypeName();
    this.administratorName = type.getAssigner().getUsername();
    this.instantiated = type.isInstantiated();
}
public ViewTypesDTO setAdministratorName(String administratorName){
    this.administratorName = administratorName;
    return this;
}


public String getAdministratorName(){
    return administratorName;
}


public boolean isInstantiated(){
    return instantiated;
}


public String getCategoryName(){
    return categoryName;
}


public ViewTypesDTO setTypeName(String typeName){
    this.typeName = typeName;
    return this;
}


public ViewTypesDTO setInstantiated(boolean instantiated){
    this.instantiated = instantiated;
    return this;
}


public ViewTypesDTO setTypeId(Long typeId){
    this.typeId = typeId;
    return this;
}


public ViewTypesDTO setCategoryName(String categoryName){
    this.categoryName = categoryName;
    return this;
}


public String getTypeName(){
    return typeName;
}


public Long getTypeId(){
    return typeId;
}


}