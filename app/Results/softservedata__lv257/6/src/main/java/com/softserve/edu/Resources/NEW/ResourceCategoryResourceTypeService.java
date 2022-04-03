package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.ResourceCategoryDAOImpl;
import com.softserve.edu.Resources.entity.ResourceCategory;
@Service
public class ResourceCategoryResourceTypeService {

@Autowired
 private ResourceCategoryDAOImpl resourcecategorydaoimpl;


public ResourceCategory getCategory(Long idWR9B){
return resourcecategorydaoimpl.getCategory(idWR9B);
}


public ResourceType setCategory(Long idWR9B,ResourceCategory category){
return resourcecategorydaoimpl.setCategory(idWR9B,category);
}


}