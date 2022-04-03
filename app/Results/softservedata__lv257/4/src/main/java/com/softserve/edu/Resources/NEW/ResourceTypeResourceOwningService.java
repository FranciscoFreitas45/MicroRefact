package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.ResourceTypeDAOImpl;
import com.softserve.edu.Resources.entity.ResourceType;
@Service
public class ResourceTypeResourceOwningService {

@Autowired
 private ResourceTypeDAOImpl resourcetypedaoimpl;


public ResourceType getResourceType(Long id2JVR){
return resourcetypedaoimpl.getResourceType(id2JVR);
}


public void setResourceType(Long id2JVR,ResourceType resourceType){
resourcetypedaoimpl.setResourceType(id2JVR,resourceType);
}


}