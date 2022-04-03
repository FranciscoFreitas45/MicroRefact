package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.dto.ResourceImplDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.Resource;
import java.util.List;
public interface ResourceService {


public List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(String ownerId)
;

public void addResource(Resource resource,ResourceImplDTO resourceImplDTO)
;

public ValidationErrorDTO validateResourceImplUniqueFields(ResourceImplDTO resourceImplDTO)
;

public List<GenericResource> findResourcesByResourceType(GenericResourceDTO genericResourceDTO)
;

public List<GenericResource> findResourcesByOwnerAndType(long ownerId,String resourceTypeName)
;

public GenericResourceDTO findResourceByTypeAndId(long resourceTypeId,long resourceId)
;

public ValidationErrorDTO validateResourceImpl(ResourceImplDTO resourceImplDTO)
;

}