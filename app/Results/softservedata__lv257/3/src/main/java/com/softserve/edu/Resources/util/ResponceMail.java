package com.softserve.edu.Resources.util;
 import com.softserve.edu.Resources.dao.impl.ResourceRequestDAOImpl;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
public class ResponceMail extends Mail{

 private  Message message;

 private  ResourceRequest resourceRequest;

public ResponceMail(Message message, ResourceRequest resourceRequest) {
    super(resourceRequest.getRegister().getUsername(), "request for a new resource type " + resourceRequest.getResourceType(), "responceEmail.vm");
    this.message = message;
    this.resourceRequest = resourceRequest;
}
public String getResourceName(){
    return resourceRequest.getResourceName();
}


public String getComment(){
    return message.getComment();
}


public String getStatus(){
    return message.getRequestStatus().toString().toLowerCase();
}


public String getResourceAdmin(){
    return resourceRequest.getResourcesAdmin().getUsername();
}


}