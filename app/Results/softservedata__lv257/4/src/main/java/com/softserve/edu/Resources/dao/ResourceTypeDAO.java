package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import java.util.List;
import java.util.Optional;
public interface ResourceTypeDAO extends GenericDAO<ResourceType, Long>{


public List<ResourceType> getInstances()
;

public void createTable(ResourceType resourceType)
;

public Optional<ResourceType> findByTypeName(String typeName)
;

public List<ResourceType> findByProperty(ResourceProperty property)
;

public Optional<ResourceType> findById(Long id,boolean doFetch)
;

public Optional<ResourceType> findByName(String name)
;

public ResourceType findWithPropertiesByID(Long resourceTypeID)
;

public List<String> getInstanceNames()
;

}