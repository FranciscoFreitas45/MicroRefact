package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.ResourceTypeDAOImpl;
import com.softserve.edu.Resources.entity.ResourceType;
@Service
public class ResourceTypeResourceCategoryService {

@Autowired
 private ResourceTypeDAOImpl resourcetypedaoimpl;


public ResourceCategory setResourceTypes(Long id,Set<ResourceType> resourceTypes){
return resourcetypedaoimpl.setResourceTypes(id,resourceTypes);
}


public Set<ResourceType> getResourceTypes(Long id){
return resourcetypedaoimpl.getResourceTypes(id);
}


}