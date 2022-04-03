package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
@Repository
public class ResourceCategoryDAOImpl extends GenericDAOImpl<ResourceCategory, Long>implements ResourceCategoryDAO{

public ResourceCategoryDAOImpl() {
    super(ResourceCategory.class);
}
@Override
public Optional<ResourceCategory> findByName(String name){
    final String findByName = "select rc from ResourceCategory rc " + "where rc.categoryName = :name";
    return querySingleResult(findByName, "name", name);
}


@Override
public List<ResourceCategory> findRootCategories(){
    Query query = em.createQuery("select rc from ResourceCategory rc where rc.parentCategory IS NULL");
    return (List<ResourceCategory>) query.getResultList();
}


public ResourceCategory getCategory(Long idWR9B)

public ResourceType setCategory(Long idWR9B,ResourceCategory category)

}