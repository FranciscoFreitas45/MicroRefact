package com.softserve.edu.Resources.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.softserve.edu.Resources.Constants;
import com.softserve.edu.Resources.Request.ResourceTypeRequest;
import com.softserve.edu.Resources.Request.Impl.ResourceTypeRequestImpl;
import com.softserve.edu.Resources.DTO.ResourceType;
@Entity
@Table(name = "RESOURCE_OWNINGS")
public class ResourceOwning {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
@Column(name = "Id")
 private  Long id;

@Transient
 private  ResourceType resourceType;

@ManyToOne
@JoinColumn(name = "RESOURCE_ID")
 private  Resource resource;

@ManyToOne
@JoinColumn(name = "OWNER_ID")
 private  Owner owner;

@Column(name = "id2JVR")
 private Long id2JVR;

@Transient
 private ResourceTypeRequest resourcetyperequest = new ResourceTypeRequestImpl();;

public ResourceOwning() {
// TODO Auto-generated constructor stub
}public ResourceOwning(ResourceType resourceType, Resource resource, Owner owner) {
    this.resourceType = resourceType;
    this.resource = resource;
    this.owner = owner;
}
public Resource getResource(){
    return resource;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((owner == null) ? 0 : owner.hashCode());
    result = prime * result + ((resource == null) ? 0 : resource.hashCode());
    result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
    return result;
}


public ResourceType getResourceType(){
  this.resourceType = resourcetyperequest.getResourceType(this.id2JVR);
return this.resourceType;
}}



@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ResourceOwning other = (ResourceOwning) obj;
    if (owner == null) {
        if (other.owner != null)
            return false;
    } else if (!owner.equals(other.owner))
        return false;
    if (resource == null) {
        if (other.resource != null)
            return false;
    } else if (!resource.equals(other.resource))
        return false;
    if (resourceType == null) {
        if (other.resourceType != null)
            return false;
    } else if (!resourceType.equals(other.resourceType))
        return false;
    return true;
}


public void setResourceType(ResourceType resourceType){
this.id2JVR = resourceType.getResourcetype() ;
resourcetyperequest.setResourceType(resourceType,this.id2JVR);
 this.resourceType = resourceType;
}



@Override
public String toString(){
    return "ResourceOwners [id=" + id + ", resourceType=" + resourceType + ", resource=" + resource + ", owner=" + owner + "]";
}


public Owner getOwner(){
    return owner;
}


public void setResource(Resource resource){
    this.resource = resource;
}


public void setOwner(Owner owner){
    this.owner = owner;
}


}