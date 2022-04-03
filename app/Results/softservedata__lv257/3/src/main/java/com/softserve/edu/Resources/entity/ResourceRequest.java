package com.softserve.edu.Resources.entity;
 import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence;
import java.util.Date;
import com.softserve.edu.Resources.Request.UserRequest;
import com.softserve.edu.Resources.Request.Impl.UserRequestImpl;
import com.softserve.edu.Resources.DTO.User;
import com.softserve.edu.Resources.Request.ResourceTypeRequest;
import com.softserve.edu.Resources.Request.Impl.ResourceTypeRequestImpl;
import com.softserve.edu.Resources.DTO.ResourceType;
@Entity
@Table(name = "RESOURCE_REQUESTS")
public class ResourceRequest {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
@Column(name = "id_request")
 private  long id;

@OneToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "id_document")
 private  Document document;

@Column(name = "resource_name")
@NotBlank(message = "Please, enter new type of resource!")
 private  String resourceName;

@Column(name = "description")
@NotBlank(message = "Please, enter description to your request!")
 private  String description;

@Transient
 private  User register;

@ManyToOne
@JoinColumn(name = "id_assigner")
 private  User resourcesAdmin;

@Enumerated(EnumType.STRING)
@Column(name = "status")
 private  Status status;

@Column(name = "updated")
 private  Date update;

@Transient
 private  ResourceType resourceType;

@Column(name = "idSND5")
 private Long idSND5;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "idO93K")
 private Long idO93K;

@Transient
 private ResourceTypeRequest resourcetyperequest = new ResourceTypeRequestImpl();;

public ResourceRequest() {
}public ResourceRequest(Document document, String resourceName, String description, User register, User resourcesAdmin, Status status, Date update, ResourceType resourceType) {
    this.document = document;
    this.resourceName = resourceName;
    this.description = description;
    this.register = register;
    this.resourcesAdmin = resourcesAdmin;
    this.status = status;
    this.update = update;
    this.resourceType = resourceType;
}
public ResourceRequest setResourcesAdmin(User resourcesAdmin){
  this.register = userrequest.setResourcesAdmin(resourcesAdmin,this.idSND5);
return this.register;
}}



public Document getDocument(){
    return document;
}


public ResourceRequest setUpdate(Date update){
    this.update = update;
    return this;
}


public Date getUpdate(){
    return update;
}


public String getResourceName(){
    return resourceName;
}


public long getId(){
    return id;
}


public ResourceRequest setDescription(String description){
    this.description = description;
    return this;
}


public ResourceRequest setRegister(User register){
  this.register = userrequest.setRegister(register,this.idSND5);
return this.register;
}}



public Status getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public ResourceRequest setStatus(Status status){
    this.status = status;
    return this;
}


public User getRegister(){
  this.register = userrequest.getRegister(this.idSND5);
return this.register;
}}



public ResourceRequest setDocument(Document document){
    this.document = document;
    return this;
}


public ResourceRequest setResourceName(String resourceName){
    this.resourceName = resourceName;
    return this;
}


@Override
public int hashCode(){
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (document == null ? 0 : document.hashCode());
    result = 31 * result + (resourceType == null ? 0 : resourceType.hashCode());
    result = 31 * result + (description == null ? 0 : description.hashCode());
    result = 31 * result + (register == null ? 0 : register.hashCode());
    result = 31 * result + (resourcesAdmin != null ? resourcesAdmin.hashCode() : 0);
    result = 31 * result + (status == null ? 0 : status.hashCode());
    result = 31 * result + (update == null ? 0 : update.hashCode());
    return result;
}


public ResourceType getResourceType(){
  this.resourceType = resourcetyperequest.getResourceType(this.idO93K);
return this.resourceType;
}}



@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ResourceRequest that = (ResourceRequest) o;
    if (id != that.id)
        return false;
    if (!document.equals(that.document))
        return false;
    if (!resourceType.equals(that.resourceType))
        return false;
    if (!description.equals(that.description))
        return false;
    if (!register.equals(that.register))
        return false;
    if (resourcesAdmin != null ? !resourcesAdmin.equals(that.resourcesAdmin) : that.resourcesAdmin != null)
        return false;
    if (status != that.status)
        return false;
    return update.equals(that.update);
}


public ResourceRequest setResourceType(ResourceType resourceType){
  this.resourceType = resourcetyperequest.setResourceType(resourceType,this.idO93K);
return this.resourceType;
}}



public User getResourcesAdmin(){
  this.register = userrequest.getResourcesAdmin(this.idSND5);
return this.register;
}}



public ResourceRequest setId(long id){
    this.id = id;
    return this;
}


@Override
public String toString(){
    return "ResourceRequest{" + "id=" + id + ", document=" + document + ", resourceType='" + resourceType + '\'' + ", description='" + description + '\'' + ", register=" + register + ", resourcesAdmin=" + resourcesAdmin + ", status=" + status + ", update=" + update + '}';
}


}