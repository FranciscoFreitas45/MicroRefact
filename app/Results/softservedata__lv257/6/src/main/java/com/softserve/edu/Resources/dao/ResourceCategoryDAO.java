package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.entity.ResourceCategory;
import java.util.List;
import java.util.Optional;
public interface ResourceCategoryDAO extends GenericDAO<ResourceCategory, Long>{


public Optional<ResourceCategory> findByName(String name)
;

public List<ResourceCategory> findRootCategories()
;

}