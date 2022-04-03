package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.ResourceTypeDAOImpl;
import com.softserve.edu.Resources.entity.ResourceType;
@Service
public class ResourceTypeResourceRequestService {

@Autowired
 private ResourceTypeDAOImpl resourcetypedaoimpl;


public ResourceType getResourceType(Long idO93K){
return resourcetypedaoimpl.getResourceType(idO93K);
}


public ResourceRequest setResourceType(Long idO93K,ResourceType resourceType){
return resourcetypedaoimpl.setResourceType(idO93K,resourceType);
}


}