package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.dto;
import com.softserve.edu.Resources.entity;
import com.softserve.edu.Resources.exception.ResourceNotFoundException;
import com.softserve.edu.Resources.service.OwnerService;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util;
import java.util.stream.Collectors;
import com.softserve.edu.Resources.Interface.ResourceTypeDAO;
@Service
public class ResourceServiceImpl implements ResourceService{

@Autowired
 private ResourceTypeDAO resourceTypeDAO;

@Autowired
 private ResourceDao resourceDao;

@Autowired
 private OwnerService ownerService;

@Autowired
 private QueryBuilder queryBuilder;


@Transactional
@Override
public List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(String ownerId){
    return resourceDao.findResourcesCountGroupedByResourceTypeForOwner(Long.parseLong(ownerId));
}


@Transactional(rollbackFor = Exception.class)
@Override
public void addResource(Resource resource,ResourceImplDTO resourceImplDTO){
    // insert in resources to get unique resource id
    resourceDao.addResource(resource);
    long resourceTypeId = resourceImplDTO.getResourceTypeId();
    ResourceType resourceType = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);
    Map<String, String> propertiesAndValues = resourceImplDTO.getPropertiesAndValues();
    long[] ownerIds = resourceImplDTO.getOwnerIds();
    Arrays.stream(ownerIds).forEach(ownerId -> {
        Owner ownerById = ownerService.getOwnerById(ownerId);
        Optional<ResourceType> resourceTypeById = resourceTypeDAO.findById(resourceImplDTO.getResourceTypeId());
        ResourceOwning resourceOwning = new ResourceOwning();
        resourceOwning.setOwner(ownerById);
        resourceOwning.setResourceType(resourceTypeById.get());
        resourceOwning.setResource(resource);
        // insert to resource ownings (every owner is new record to this table)
        resourceDao.addResourceOwning(resourceOwning);
    });
    String readyQuery = queryBuilder.buildInsertResourceImplQuery(resourceType, propertiesAndValues);
    System.out.println(readyQuery);
    // insert into concrete resource table (e.g., Cars, Trucks etc.)
    resourceDao.addResourceImpl(readyQuery, resourceType, resource.getId(), propertiesAndValues);
}


@Override
public ValidationErrorDTO validateResourceImplUniqueFields(ResourceImplDTO resourceImplDTO){
    ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
    long resourceTypeId = resourceImplDTO.getResourceTypeId();
    ResourceType resourceType = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);
    List<ConstrainedProperty> uniqueProperties = resourceType.getProperties().stream().filter(ConstrainedProperty::isUnique).collect(Collectors.toList());
    String tableName = resourceType.getTableName();
    Map<String, String> valuesToSearch = new HashMap<>();
    List<ConstrainedProperty> uniqueProperty = new ArrayList<>();
    // check exactly one and every unique property
    // if there is resource in the table with such unique value, populate errorDto
    uniqueProperties.forEach(constrainedProperty -> {
        String columnName = constrainedProperty.getProperty().getColumnName();
        String columnValue = resourceImplDTO.getPropertiesAndValues().get(columnName);
        valuesToSearch.put(columnName, columnValue);
        uniqueProperty.add(constrainedProperty);
        String queryForDao = queryBuilder.queryForJdbcTemplate(tableName, valuesToSearch, uniqueProperty);
        List<GenericResource> resourcesByResourceType = resourceDao.findResourcesByResourceType(queryForDao, valuesToSearch, uniqueProperty);
        System.out.println(resourcesByResourceType);
        if (!resourcesByResourceType.isEmpty()) {
            System.out.println("adding FieldErrorDto to column: " + columnName);
            validationErrorDTO.addFieldError(columnName, "This value already exists. Please, specify another value.");
        }
        valuesToSearch.clear();
        uniqueProperty.clear();
    });
    return validationErrorDTO;
}


@Transactional
@Override
public List<GenericResource> findResourcesByResourceType(GenericResourceDTO resourceDTO) throws ResourceNotFoundException{
    long resourceTypeId = resourceDTO.getId();
    ResourceType resourceType = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);
    if (resourceType == null) {
        throw new ResourceNotFoundException("No infromation was found by your request");
    }
    String tableName = resourceType.getTableName();
    List<ConstrainedProperty> resourceProperties = new ArrayList<>(resourceType.getProperties());
    Map<String, String> valuesToSearch = resourceDTO.getResourcePropertyValues();
    /*
         * validation of keyValues: do such columnNames exists for this resource
         * type
         */
    // TODO: use security service to get allowed properties to search for
    // the client
    Set<String> propertyColumnNames = valuesToSearch.keySet();
    boolean columnNameExist = false;
    for (String columnName : propertyColumnNames) {
        for (ConstrainedProperty constrainedProperty : resourceProperties) {
            if (columnName.equalsIgnoreCase(constrainedProperty.getProperty().getColumnName())) {
                columnNameExist = true;
                break;
            } else {
                columnNameExist = false;
            }
        }
        if (!columnNameExist) {
            throw new ResourceNotFoundException("You've requested wrong data.");
        }
    }
    /*
         * validation of client inputs: do they matches with pattern for
         * concrete resource property
         */
    boolean match = false;
    for (Map.Entry<String, String> columnNamesAndInputs : valuesToSearch.entrySet()) {
        for (ConstrainedProperty constrainedProperty : resourceProperties) {
            if (columnNamesAndInputs.getKey().equalsIgnoreCase(constrainedProperty.getProperty().getColumnName())) {
                match = columnNamesAndInputs.getValue().matches(constrainedProperty.getProperty().getPattern());
                break;
            }
        }
        if (!match) {
            throw new ResourceNotFoundException("You've requested wrong data.");
        }
    }
    String queryForDao = queryBuilder.queryForJdbcTemplate(tableName, valuesToSearch, resourceProperties);
    return resourceDao.findResourcesByResourceType(queryForDao, valuesToSearch, resourceProperties);
}


@Transactional
@Override
public List<GenericResource> findResourcesByOwnerAndType(long ownerId,String resourceTypeName) throws ResourceNotFoundException{
    List<Long> resourcesIds = resourceDao.findResourcesIdsByOwner(ownerId, resourceTypeName);
    Optional<ResourceType> resourceType = resourceTypeDAO.findByTypeName(resourceTypeName);
    String tableName;
    if (resourceType.isPresent()) {
        tableName = resourceType.get().getTableName();
    } else {
        throw new ResourceNotFoundException("You've requested wrong data.");
    }
    List<ConstrainedProperty> resourceProperties = new ArrayList<>(resourceType.get().getProperties());
    System.out.println(resourceProperties);
    String sqlQuery = queryBuilder.namedQueryForLookingByResourcesIds(tableName, resourceProperties);
    return resourceDao.findResourcesByOwnerAndResourcesType(sqlQuery, resourceProperties, resourcesIds);
}


@Transactional
public GenericResourceDTO findResourceByTypeAndId(long resourceTypeId,long resourceId){
    ResourceType resourceType = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);
    if (resourceType == null) {
        throw new ResourceNotFoundException("No infromation was found by your request");
    }
    String tableName = resourceType.getTableName();
    List<ConstrainedProperty> resourceProperties = new ArrayList<>(resourceType.getProperties());
    String sqlQuery = queryBuilder.namedQueryForLookingByResourceId(tableName, resourceProperties);
    GenericResourceDTO genericResource = resourceDao.findById(resourceId, sqlQuery, resourceProperties);
    genericResource.setResourceTypeName(resourceType.getTypeName());
    genericResource.setOwners(resourceDao.getOwnersForGenericResourceByResourceTypeAndResource(resourceTypeId, resourceId));
    genericResource.setAddress(resourceDao.findAddressForGenericResourceByResourceId(resourceId));
    return genericResource;
}


@Override
public ValidationErrorDTO validateResourceImpl(ResourceImplDTO resourceImplDTO){
    ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
    Map<String, String> propertiesAndValues = resourceImplDTO.getPropertiesAndValues();
    long resourceTypeId = resourceImplDTO.getResourceTypeId();
    ResourceType resourceTypeWithProperties = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);
    Set<ConstrainedProperty> properties = resourceTypeWithProperties.getProperties();
    properties.forEach(constrainedProperty -> {
        String columnName = constrainedProperty.getProperty().getColumnName();
        String columnValue = propertiesAndValues.get(columnName);
        if ((columnValue == null || columnValue.isEmpty()) && constrainedProperty.isRequired()) {
            validationErrorDTO.addFieldError(columnName, "This field is required");
        } else if (!columnValue.matches(constrainedProperty.getProperty().getPattern())) {
            validationErrorDTO.addFieldError(columnName, "This field do not matches valid format");
        }
    });
    return validationErrorDTO;
}


}