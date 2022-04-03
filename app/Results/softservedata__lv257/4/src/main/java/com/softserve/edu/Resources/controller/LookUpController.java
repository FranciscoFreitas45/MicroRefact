package com.softserve.edu.Resources.controller;
 import com.softserve.edu.Resources.dto.ExceptionJSONInfo;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.ResourceNotFoundException;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.softserve.edu.Resources.Interface.ResourceService;
@RestController
@RequestMapping(value = "/api/resources/lookup", method = RequestMethod.GET)
public class LookUpController {

@Autowired
 private ResourceTypeService resourceTypeService;

@Autowired
 private ResourceService resourceService;


@RequestMapping(value = "/owners/{ownerId}/groupedresources", method = RequestMethod.GET)
public List<GroupedResourceCount> findAllOwnerResourcesGroupedByResourceType(String ownerId){
    List<GroupedResourceCount> groupedResources = resourceService.findResourcesCountGroupedByResourceTypeForOwner(ownerId);
    if (groupedResources.isEmpty()) {
        throw new ResourceNotFoundException("No infromation was found by your request");
    }
    return groupedResources;
}


@ExceptionHandler(ResourceNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public ExceptionJSONInfo noInfoFound(ResourceNotFoundException e){
    String message = e.getMessage();
    ExceptionJSONInfo exceptionJson = new ExceptionJSONInfo();
    exceptionJson.setMessage(message);
    return exceptionJson;
}


@RequestMapping(value = "/inputedvalues/foundresources", method = RequestMethod.POST)
public List<GenericResource> getValuesFromForm(GenericResourceDTO resourceDTO){
    List<GenericResource> genResList = resourceService.findResourcesByResourceType(resourceDTO);
    if (genResList.isEmpty()) {
        throw new ResourceNotFoundException("No infromation was found by your request");
    }
    return genResList;
}


@RequestMapping(value = "/resourcetypes/{resourceTypeId}", method = RequestMethod.GET)
public List<ResourceProperty> loadSpecResourceProperty(String resourceTypeId){
    ResourceType resourceType = resourceTypeService.findWithPropertiesByID(Long.parseLong(resourceTypeId));
    if (resourceType == null) {
        throw new ResourceNotFoundException("No resourece type was found by your request");
    }
    List<ConstrainedProperty> constraintProperties = resourceTypeService.getSearchableProperties(resourceType);
    List<ResourceProperty> resourceProperties = new ArrayList<>();
    for (ConstrainedProperty constraint : constraintProperties) {
        resourceProperties.add(constraint.getProperty());
    }
    if (resourceProperties.isEmpty()) {
        throw new ResourceNotFoundException("No resource properties were found by your request");
    }
    Collections.sort(resourceProperties);
    return resourceProperties;
}


@RequestMapping(value = "/owners/{ownerId}/resourcetypes/{resourceTypeName}/foundresources", method = RequestMethod.GET)
public List<GenericResource> lookUpByOwner(long ownerId,String resourceTypeName){
    List<GenericResource> genericResources = resourceService.findResourcesByOwnerAndType(ownerId, resourceTypeName);
    if (genericResources.isEmpty()) {
        throw new ResourceNotFoundException("No infromation was found by your request");
    }
    return genericResources;
}


}