package com.softserve.edu.Resources.dto;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.edu.Resources.entity.ResourceType;
public class ResourceTypeDTO {

@JsonView(Views.CategoriesWithTypes.class)
 private  long id;

@JsonView(Views.CategoriesWithTypes.class)
 private  String typeName;

@JsonView(Views.CategoriesWithTypes.class)
@JsonBackReference
 private  ResourceCategoryDTO category;

public ResourceTypeDTO() {
}public ResourceTypeDTO(Long id, String typeName, String tableName) {
    this.id = id;
    this.typeName = typeName;
}public ResourceTypeDTO(ResourceType resourceType) {
    this.id = resourceType.getId();
    this.typeName = resourceType.getTypeName();
}
public void setCategory(ResourceCategoryDTO category){
    this.category = category;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


public ResourceCategoryDTO getCategory(){
    return category;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public String getTypeName(){
    return typeName;
}


}