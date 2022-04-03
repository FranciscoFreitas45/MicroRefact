package com.softserve.edu.Resources.entity;
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
@Entity
@NamedEntityGraph(name = "TypesProperties", attributeNodes = { @NamedAttributeNode("properties") })
@Table(name = "RESOURCE_TYPES")
public class ResourceType {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
@Column(name = "Id")
 private  Long id;

@NotNull
@Column(name = "Type_Name", unique = true, nullable = false)
 private  String typeName;

@NotNull
@Column(name = "Table_Name", unique = true, nullable = false)
 private  String tableName;

@Transient
 private  ResourceCategory category;

@ElementCollection(fetch = FetchType.LAZY)
@CollectionTable(name = "Type_Properties", joinColumns = @JoinColumn(name = "Type_Id"))
 private  Set<ConstrainedProperty> properties;

@Column(name = "Instantiated")
 private  boolean instantiated;

@Transient
 private  User assigner;

@Transient
 private  ResourceRequest request;

@Column(name = "idWR9B")
 private Long idWR9B;

@Transient
 private ResourceCategoryRequest resourcecategoryrequest = new ResourceCategoryRequestImpl();;

@Column(name = "id93GK")
 private Long id93GK;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "idW6RM")
 private long idW6RM;

@Transient
 private ResourceRequestRequest resourcerequestrequest = new ResourceRequestRequestImpl();;

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
  this.request = resourcerequestrequest.setRequest(request,this.idW6RM);
return this.request;
}}



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
  this.request = resourcerequestrequest.getRequest(this.idW6RM);
return this.request;
}}



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
  this.category = resourcecategoryrequest.getCategory(this.idWR9B);
return this.category;
}}



public Long getId(){
    return id;
}


public void addProperty(ConstrainedProperty constrainedProperty){
    properties.add(constrainedProperty);
}


public ResourceType setCategory(ResourceCategory category){
  this.category = resourcecategoryrequest.setCategory(category,this.idWR9B);
return this.category;
}}



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
  this.assigner = userrequest.setAssigner(assigner,this.id93GK);
return this.assigner;
}}



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
  this.assigner = userrequest.getAssigner(this.id93GK);
return this.assigner;
}}



public void removeProperty(ConstrainedProperty constrainedProperty){
    properties.remove(constrainedProperty);
}


}