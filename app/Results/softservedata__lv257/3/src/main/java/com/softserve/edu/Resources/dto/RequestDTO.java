package com.softserve.edu.Resources.dto;
 import com.fasterxml.jackson.annotation.JsonFormat;
import com.softserve.edu.Resources.entity.ResourceRequest;
import org.omg.CORBA.Request;
import java.util.Date;
public class RequestDTO {

 private  Long id;

 private  String requesterName;

 private  String assignerName;

 private  String resourceName;

 private  String description;

 private  String resourceType;

 private  String resourceTypeId;

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+2")
 private  Date update;

 private  ResourceRequest.Status status;

public RequestDTO() {
}public RequestDTO(ResourceRequest request) {
    this.id = request.getId();
    if (request.getRegister() != null) {
        this.requesterName = request.getRegister().getUsername();
    } else {
        this.requesterName = null;
    }
    if (request.getResourcesAdmin() != null) {
        this.assignerName = request.getResourcesAdmin().getUsername();
    } else {
        this.assignerName = null;
    }
    if (request.getResourceType() != null) {
        this.resourceType = request.getResourceType().getTypeName();
        this.resourceTypeId = request.getResourceType().getId().toString();
    } else {
        this.resourceType = null;
        this.resourceTypeId = null;
    }
    this.resourceName = request.getResourceName();
    this.description = request.getDescription();
    this.update = request.getUpdate();
    this.status = request.getStatus();
}
public String getRequesterName(){
    return requesterName;
}


public void setRequesterName(String requesterName){
    this.requesterName = requesterName;
}


public String getAssignerName(){
    return assignerName;
}


public void setUpdate(Date update){
    this.update = update;
}


public void setResourceTypeId(String resourceTypeId){
    this.resourceTypeId = resourceTypeId;
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


public void setAssignerName(String assignerName){
    this.assignerName = assignerName;
}


public void setDescription(String description){
    this.description = description;
}


public ResourceRequest.Status getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public void setStatus(ResourceRequest.Status status){
    this.status = status;
}


public void setResourceName(String resourceName){
    this.resourceName = resourceName;
}


public String getResourceTypeId(){
    return resourceTypeId;
}


public String getResourceType(){
    return resourceType;
}


public void setResourceType(String resourceType){
    this.resourceType = resourceType;
}


public void setId(long id){
    this.id = id;
}


}