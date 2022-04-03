package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.ResourceRequestDAOImpl;
import com.softserve.edu.Resources.entity.ResourceRequest;
@Service
public class ResourceRequestResourceTypeService {

@Autowired
 private ResourceRequestDAOImpl resourcerequestdaoimpl;


public ResourceType setRequest(long idW6RM,ResourceRequest request){
return resourcerequestdaoimpl.setRequest(idW6RM,request);
}


public ResourceRequest getRequest(long idW6RM){
return resourcerequestdaoimpl.getRequest(idW6RM);
}


}