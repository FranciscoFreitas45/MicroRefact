package com.softserve.edu.Resources.DTO;
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
public class ResourceRequest {

 private  long id;

 private  Document document;

 private  String resourceName;

 private  String description;

 private  User register;

 private  User resourcesAdmin;

 private  Status status;

 private  Date update;

 private  ResourceType resourceType;

 private Long idSND5;

 private Long idO93K;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

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
public Document getDocument(){
    return document;
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


public Status getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public User getRegister(){
  this.register = userrequest.getRegister(this.idSND5);
return this.register;
}}



public ResourceType getResourceType(){
  this.resourceType = resourcetyperequest.getResourceType(this.idO93K);
return this.resourceType;
}}



public User getResourcesAdmin(){
  this.register = userrequest.getResourcesAdmin(this.idSND5);
return this.register;
}}



public ResourceRequest setResourceType(ResourceType resourceType){
  this.resourceType = resourcetyperequest.setResourceType(resourceType,this.idO93K);
return this.resourceType;
  


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setResourceType"))

.queryParam("resourceType",resourceType)
;
ResourceRequest aux = restTemplate.getForObject(builder.toUriString(),ResourceRequest.class);
return aux;
}


public ResourceRequest setStatus(Status status){
    this.status = status;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setStatus"))

.queryParam("status",status)
;
ResourceRequest aux = restTemplate.getForObject(builder.toUriString(),ResourceRequest.class);
return aux;
}


}