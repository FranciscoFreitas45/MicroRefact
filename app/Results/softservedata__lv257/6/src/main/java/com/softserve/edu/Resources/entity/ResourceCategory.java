package com.softserve.edu.Resources.entity;
 import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.HashSet;
import java.util.Set;
import com.softserve.edu.Resources.Request.ResourceTypeRequest;
import com.softserve.edu.Resources.Request.Impl.ResourceTypeRequestImpl;
import com.softserve.edu.Resources.DTO.ResourceType;
@Entity
@Table(name = "RESOURCE_CATEGORIES")
public class ResourceCategory {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
@Column(name = "Id")
 private  Long id;

@Column(name = "Category_Name", unique = true, nullable = false)
 private  String categoryName;

@ManyToOne
@JoinColumn(name = "Id_Parent")
 private  ResourceCategory parentCategory;

@OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
 private  Set<ResourceCategory> childrenCategories;

@Transient
 private  Set<ResourceType> resourceTypes;

@Transient
 private ResourceTypeRequest resourcetyperequest = new ResourceTypeRequestImpl();;

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


public ResourceCategory setResourceTypes(Set<ResourceType> resourceTypes){
  this.resourceTypes = resourcetyperequest.setResourceTypes(resourceTypes,this.id);
return this.resourceTypes;
}}



public Long getId(){
    return id;
}


public ResourceCategory setParentCategory(ResourceCategory parentCategory){
    this.parentCategory = parentCategory;
    return this;
}


public ResourceCategory setCategoryName(String categoryName){
    this.categoryName = categoryName;
    return this;
}


public Set<ResourceCategory> getChildrenCategories(){
    return childrenCategories;
}


public ResourceCategory setChildrenCategories(Set<ResourceCategory> childrenCategories){
    this.childrenCategories = childrenCategories;
    return this;
}


@Override
public int hashCode(){
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + categoryName.hashCode();
    return result;
}


public ResourceCategory getParentCategory(){
    return parentCategory;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ResourceCategory category = (ResourceCategory) o;
    if (id != null ? !id.equals(category.id) : category.id != null)
        return false;
    return categoryName.equals(category.categoryName);
}


public ResourceCategory setId(Long id){
    this.id = id;
    return this;
}


@Override
public String toString(){
    return "ResourceCategory{" + "id=" + id + ", categoryName='" + categoryName + '\'' + '}';
}


public Set<ResourceType> getResourceTypes(){
  this.resourceTypes = resourcetyperequest.getResourceTypes(this.id);
return this.resourceTypes;
}}



}