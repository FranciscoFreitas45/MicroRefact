package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.dto.ResourceTypeBrief;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.exception.ResourceTypeInstantiationException;
import com.softserve.edu.Resources.exception.ResourceTypeNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
public interface ResourceTypeService {


public List<ResourceType> getInstances()
;

public void removeById(Long id) throws ResourceTypeNotFoundException
;

public void instantiateType(Long id) throws ResourceTypeNotFoundException
;

public int getInstancesCount()
;

public Optional<ResourceType> get(Long id,boolean fetch)
;

public List<ConstrainedProperty> getSearchableProperties(ResourceType resourceWithProperties)
;

public ResourceType save(ResourceTypeBrief resourceType,User resourceAdmin)
;

public ResourceType findWithPropertiesByID(Long ID)
;

public long getTypeCount()
;

public Collection<ResourceType> getTypes()
;

public void createBatch(List<Long> IDs)
;

public void remove(ResourceType resourceType)
;

}