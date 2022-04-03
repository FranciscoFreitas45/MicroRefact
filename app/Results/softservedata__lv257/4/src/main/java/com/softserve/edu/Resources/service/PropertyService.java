package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.dto.ResourcePropertyDescription;
import com.softserve.edu.Resources.dto.ValueTypeDTO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import java.util.List;
import java.util.Optional;
import java.util.Set;
public interface PropertyService {


public ResourceProperty add(ResourceProperty property)
;

public Long propertiesCount()
;

public List<ResourcePropertyDescription> getPropertyDescriptions()
;

public Optional<ResourceProperty> getProperty(String description)
;

public Set<ResourceProperty> getProperties(String propertyName)
;

public ResourceProperty update(ResourceProperty property)
;

public List<ValueTypeDTO> getValueTypes()
;

public Set<Long> getPropertyIDs()
;

public Optional<ResourceProperty> getPropertyById(Long propID)
;

}