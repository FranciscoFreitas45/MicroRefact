package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.dto.ConstrainedPropertyBrief;
import com.softserve.edu.Resources.dto.ResourceTypeBrief;
import com.softserve.edu.Resources.entity;
import com.softserve.edu.Resources.exception.InvalidResourceCategoryException;
import com.softserve.edu.Resources.exception.InvalidResourceTypeException;
import com.softserve.edu.Resources.exception.ResourceTypeInstantiationException;
import com.softserve.edu.Resources.exception.ResourceTypeNotFoundException;
import com.softserve.edu.Resources.service.PropertyService;
import com.softserve.edu.Resources.service.ResourceCategoryService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.invoke.MethodHandles;
import java.util;
import java.util.stream.Collectors;
import com.softserve.edu.Resources.Interface.ResourceCategoryService;
@Service("resourceTypeService")
@Transactional
public class ResourceTypeServiceImpl implements ResourceTypeService{

 public  Logger LOGGER;

 private  ResourceTypeDAO resourceTypeDAO;

 private  ResourceCategoryService resourceCategoryService;

 private  PropertyService propertyService;

@Autowired
public ResourceTypeServiceImpl(ResourceTypeDAO resourceTypeDAO, ResourceCategoryService resourceCategoryService, PropertyService propertyService) {
    this.resourceTypeDAO = resourceTypeDAO;
    this.resourceCategoryService = resourceCategoryService;
    this.propertyService = propertyService;
}
@Override
public List<ResourceType> getInstances(){
    return resourceTypeDAO.getInstances();
}


@Override
public void removeById(Long id) throws ResourceTypeNotFoundException{
    Optional<ResourceType> resourceType = get(id);
    if (!resourceType.isPresent())
        throw new ResourceTypeNotFoundException("Requested Resource Type not found.");
    if (resourceType.get().isInstantiated())
        throw new ResourceTypeInstantiationException("Instantiated Resource Type can not be removed");
    remove(resourceType.get());
    resourceTypeDAO.makeTransient(resourceType.get());
}


@Override
public void instantiateType(Long id) throws ResourceTypeNotFoundException{
    final Optional<ResourceType> resourceType = resourceTypeDAO.findById(id);
    if (!resourceType.isPresent())
        throw new ResourceTypeNotFoundException("Requested Resource Type not found.");
    if (resourceType.get().isInstantiated())
        throw new ResourceTypeInstantiationException("Resource Type is already instantiated");
    if (resourceType.get().getProperties().size() < 1)
        throw new ResourceTypeInstantiationException("Resource Type should have at least " + "one Resource Property before instantiating");
    resourceTypeDAO.createTable(resourceType.get());
    resourceType.get().setInstantiated(true);
    resourceTypeDAO.makePersistent(resourceType.get());
}


@Override
public List<ConstrainedProperty> getSearchableProperties(ResourceType resourceType){
    return resourceType.getProperties().stream().filter(ConstrainedProperty::isSearchable).collect(Collectors.toList());
}


@Override
public ResourceType save(ResourceTypeBrief resourceTypeBrief,User resourceAdmin){
    Long id = resourceTypeBrief.getId();
    ResourceType resourceType;
    if (id == null || id == 0) {
        resourceType = new ResourceType();
    } else {
        Optional<ResourceType> existentResourceType = resourceTypeDAO.findById(id);
        if (!existentResourceType.isPresent()) {
            LOGGER.warn("Request contains invalid Resource Type reference (id = {})", id);
            throw new InvalidResourceTypeException(String.format("ResourceType with id \"%d\" not found", id));
        }
        resourceType = existentResourceType.get();
    }
    long categoryId = resourceTypeBrief.getCategoryId();
    Optional<ResourceCategory> category = resourceCategoryService.findCategoryById(categoryId);
    if (!category.isPresent()) {
        LOGGER.warn("Request contains invalid Resource Category reference (id = {})", categoryId);
        throw new InvalidResourceCategoryException(String.format("Category \"%s\" not found", categoryId));
    }
    resourceType.setTypeName(resourceTypeBrief.getTypeName()).setTableName(resourceTypeBrief.getTableName()).setCategory(category.get());
    Set<Long> updatedPropertyIDs = resourceTypeBrief.getProperties().stream().map(ConstrainedPropertyBrief::getId).collect(Collectors.toSet());
    Set<Long> availablePropertyIDs = propertyService.getPropertyIDs();
    Set<Long> validIDs = new HashSet<>(updatedPropertyIDs);
    validIDs.removeIf(propId -> !availablePropertyIDs.contains(propId));
    if (updatedPropertyIDs.size() != validIDs.size()) {
        resourceTypeBrief.getProperties().removeIf(cpb -> !validIDs.contains(cpb.getId()));
        LOGGER.warn("A few invalid properties requested - {}", updatedPropertyIDs.removeAll(validIDs));
    }
    Set<ResourceProperty> updatedTypeProperties = validIDs.stream().map(propertyService::getPropertyById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
    Set<ConstrainedProperty> typeProperties = resourceTypeBrief.getProperties().stream().map(cpb -> new ConstrainedProperty().setProperty(propertyService.getPropertyById(cpb.getId()).get()).setRequired(cpb.isRequired()).setSearchable(cpb.isSearchable()).setUnique(cpb.isUnique())).collect(Collectors.toSet());
    resourceType.setProperties(typeProperties);
    resourceType.setAssigner(resourceAdmin);
    return resourceTypeDAO.makePersistent(resourceType);
}


@Override
public ResourceType findWithPropertiesByID(Long ID){
    return resourceTypeDAO.findWithPropertiesByID(ID);
}


@Override
public long getTypeCount(){
    return resourceTypeDAO.getCount();
}


@Override
public void createBatch(List<Long> IDs){
    IDs.forEach(this::instantiateType);
}


public ResourceTypeService instantiate(Map<ResourceType,String> instances){
    return this;
}


@Override
public void remove(ResourceType resourceType){
    resourceTypeDAO.makeTransient(resourceType);
}


@Override
public int getInstancesCount(){
    return getInstances().size();
}


@Override
public Optional<ResourceType> get(Long id,boolean doFetch){
    return resourceTypeDAO.findById(id, doFetch);
}


@Override
public List<ResourceType> getTypes(){
    return resourceTypeDAO.findAll();
}


}