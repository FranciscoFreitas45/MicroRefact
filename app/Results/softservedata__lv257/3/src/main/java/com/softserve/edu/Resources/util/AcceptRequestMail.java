package com.softserve.edu.Resources.util;
 import com.softserve.edu.Resources.entity.ResourceRequest;
public class AcceptRequestMail extends Mail{

 private  ResourceRequest resourceRequest;

public AcceptRequestMail(ResourceRequest request) {
    super(request.getRegister().getUsername(), request.getResourceType().getTypeName() + " resource type is created", "createResourceTypeNotification.vm");
    resourceRequest = request;
}
public String getResourceType(){
    return resourceRequest.getResourceType().getTypeName();
}


public String getResourceName(){
    return resourceRequest.getResourceName();
}


}