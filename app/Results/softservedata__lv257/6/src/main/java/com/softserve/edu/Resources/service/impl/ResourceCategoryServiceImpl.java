package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.dto.ViewTypesDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.InvalidResourceCategoryException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.invoke.MethodHandles;
import java.util;
import java.util.stream.Collectors;
@Service
public class ResourceCategoryServiceImpl {

 static  Logger logger;

@Autowired
 private  ResourceCategoryDAO resourceCategoryDAO;


@Override
@Transactional
public List<ResourceCategory> findAllResourceCategories(){
    logger.info("Finding all categories");
    return resourceCategoryDAO.findAll();
}


public List<ResourceCategory> getAncestors(ResourceCategory resourceCategory,Set<ResourceCategory> visited) throws CycleDependencyException{
    logger.info("Getting ancestor categories for category " + resourceCategory);
    List<ResourceCategory> ancestors = new ArrayList<>();
    visited.add(resourceCategory);
    ResourceCategory parent = resourceCategory.getParentCategory();
    if (parent != null) {
        if (!visited.contains(parent)) {
            ancestors.addAll(getAncestors(parent, visited));
        } else {
            logger.warn("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
            throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
        }
        ancestors.add(parent);
    }
    return ancestors;
}


@Override
@Transactional
public Optional<ResourceCategory> findCategoryByName(String categoryName){
    logger.info("Finding category by name " + categoryName);
    return resourceCategoryDAO.findByName(categoryName);
}


@Override
public ResourceCategoryDTO createCategoryDTO(ResourceCategory category){
    return createCategoryDTO(category, new HashSet<>());
}


@Override
@Transactional
public ResourceCategory saveResourceCategory(ResourceCategory resourceCategory){
    logger.info("Saving category " + resourceCategory);
    return resourceCategoryDAO.makePersistent(resourceCategory);
}


@Override
@Transactional
public ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO){
    return mapFromDtoToResourceCategory(categoryDTO, new HashSet<>());
}


@Override
@Transactional
public Optional<ResourceCategory> findCategoryById(Long id){
    logger.info("Finding category by ID " + id);
    return resourceCategoryDAO.findById(id);
}


@Override
@Transactional
public void deleteResourceCategory(ResourceCategory resourceCategory){
    logger.info("Removing category " + resourceCategory);
    resourceCategoryDAO.makeTransient(resourceCategory);
}


@Override
public List<ResourceCategory> deployCategory(ResourceCategory rootCategory){
    logger.info("Deploying category " + rootCategory + " and its nested categories into flat list of categories");
    List<ResourceCategory> allCategories = new ArrayList<>(Arrays.asList(rootCategory));
    allCategories.addAll(getDescendants(rootCategory));
    return allCategories;
}


public List<ResourceCategory> getDescendants(ResourceCategory resourceCategory,Set<ResourceCategory> visited) throws CycleDependencyException{
    logger.info("Getting nested categories for category " + resourceCategory);
    List<ResourceCategory> descendants = new ArrayList<>();
    visited.add(resourceCategory);
    if (resourceCategory.getChildrenCategories() != null && !resourceCategory.getChildrenCategories().isEmpty()) {
        for (ResourceCategory rc : resourceCategory.getChildrenCategories()) {
            if (!visited.contains(rc)) {
                descendants.add(rc);
            } else {
                logger.warn("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
                throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
            }
            descendants.addAll(getDescendants(rc, visited));
        }
    }
    return descendants;
}


@Override
public List<ResourceCategory> deployAllCategoriesFromRoots(List<ResourceCategory> rootCategories) throws CycleDependencyException{
    logger.info("Deploying list of categories and its nested categories into flat list of categories");
    List<ResourceCategory> allCategories = new ArrayList<>(rootCategories);
    rootCategories.forEach(c -> allCategories.addAll(getDescendants(c)));
    if (allCategories.size() > new HashSet<>(allCategories).size()) {
        logger.warn("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
        throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
    }
    return allCategories;
}


@Override
@Transactional
public void deleteMissingCategoriesInDB(List<ResourceCategory> allCategoriesFromWeb) throws RemovingCategoriesWithTypesException{
    logger.info("Removing categories which are absent in Database after managing");
    List<ResourceCategory> allCategoriesFromDB = findAllResourceCategories();
    allCategoriesFromDB.stream().filter(c -> !allCategoriesFromWeb.stream().map(ResourceCategory::getId).collect(Collectors.toList()).contains(c.getId())).forEach(dc -> {
        if (dc.getResourceTypes().size() == 0 && getDescendants(dc).stream().allMatch(dcd -> dcd.getResourceTypes().size() == 0)) {
            deleteResourceCategory(dc);
        } else {
            logger.warn("Can not remove from database resource categories which have resource types");
            throw new RemovingCategoriesWithTypesException("Can not remove from database resource categories which have resource types");
        }
    });
    resourceCategoryDAO.flush();
}


@Override
public boolean isValidCategoryName(List<ResourceCategory> categories,int minNameLength,int maxNameLength){
    logger.info("Checking categories for valid names");
    List<String> usedNames = new ArrayList<>();
    for (ResourceCategory category : categories) {
        String name = category.getCategoryName();
        if (name == null || usedNames.contains(name.toLowerCase()) || name.length() < minNameLength || name.length() > maxNameLength) {
            return false;
        }
        usedNames.add(name.toLowerCase());
    }
    return true;
}


@Override
@Transactional
public List<ResourceCategory> findRootCategories(){
    logger.info("Finding root categories");
    return resourceCategoryDAO.findRootCategories();
}


}