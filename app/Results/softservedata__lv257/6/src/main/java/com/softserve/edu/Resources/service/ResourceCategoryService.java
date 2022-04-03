package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
public interface ResourceCategoryService {


@Transactional
public List<ResourceCategory> findAllResourceCategories()
;

public List<ResourceCategory> getAncestors(ResourceCategory resourceCategory) throws CycleDependencyException
;

@Transactional
public Optional<ResourceCategory> findCategoryByName(String categoryName)
;

public ResourceCategoryDTO createCategoryDTO(ResourceCategory category)
;

@Transactional
public ResourceCategory saveResourceCategory(ResourceCategory resourceCategory)
;

@Transactional
public ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO)
;

@Transactional
public Optional<ResourceCategory> findCategoryById(Long id)
;

@Transactional
public void deleteResourceCategory(ResourceCategory resourceCategory)
;

public List<ResourceCategory> deployCategory(ResourceCategory rootCategory)
;

public List<ResourceCategory> getDescendants(ResourceCategory resourceCategory) throws CycleDependencyException
;

public List<ResourceCategory> deployAllCategoriesFromRoots(List<ResourceCategory> rootCategories) throws CycleDependencyException
;

@Transactional
public void deleteMissingCategoriesInDB(List<ResourceCategory> allCategoriesFromWeb) throws RemovingCategoriesWithTypesException
;

public boolean isValidCategoryName(List<ResourceCategory> categories,int minNameLength,int maxNameLength)
;

@Transactional
public List<ResourceCategory> findRootCategories()
;

}