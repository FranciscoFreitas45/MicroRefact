package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.entity;
import java.util.List;
import java.util.Map;
public interface ResourceDao {


public List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(Long ownerId)
;

public List<Long> findResourcesIdsByOwner(long ownerId,String resourceTypeName)
;

public List<GenericResource> findResourcesByOwnerAndResourcesType(String sqlQuery,List<ConstrainedProperty> resourcesProperties,List<Long> resourcesIds)
;

public void addResource(Resource resource)
;

public List<Owner> getOwnersForGenericResourceByResourceTypeAndResource(long resourceTypeId,long resourceId)
;

public GenericResourceDTO findById(long resourceId,String sqlQuery,List<ConstrainedProperty> resourceProperties)
;

public void addResourceOwning(ResourceOwning resourceOwning)
;

public List<GenericResource> findResourcesByResourceType(String sqlQuery,Map<String,String> valuesToSearh,List<ConstrainedProperty> resourceProperties)
;

public void addResourceImpl(String query,ResourceType resourceType,long resourceImplId,Map<String,String> propertiesAndValues)
;

public Address findAddressForGenericResourceByResourceId(long resourceId)
;

}