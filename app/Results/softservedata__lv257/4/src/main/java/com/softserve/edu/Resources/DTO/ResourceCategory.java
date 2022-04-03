package com.softserve.edu.Resources.DTO;
 import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.HashSet;
import java.util.Set;
public class ResourceCategory {

 private  Long id;

 private  String categoryName;

 private  ResourceCategory parentCategory;

 private  Set<ResourceCategory> childrenCategories;

 private  Set<ResourceType> resourceTypes;

public ResourceCategory(String categoryName) {
    this.categoryName = categoryName;
}public ResourceCategory() {
}public ResourceCategory(String categoryName, ResourceCategory parentCategory) {
    this.categoryName = categoryName;
    this.parentCategory = parentCategory;
}
public String getCategoryName(){
    return categoryName;
}


public Long getId(){
    return id;
}


public Set<ResourceCategory> getChildrenCategories(){
    return childrenCategories;
}


public ResourceCategory getParentCategory(){
    return parentCategory;
}


public Set<ResourceType> getResourceTypes(){
    return resourceTypes;
}


}