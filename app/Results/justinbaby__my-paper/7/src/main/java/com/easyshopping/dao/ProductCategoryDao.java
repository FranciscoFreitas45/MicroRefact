package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.entity.ProductCategory;
public interface ProductCategoryDao extends BaseDao<ProductCategory, Long>{


public List<ProductCategory> findRoots(Integer count)
;

public List<ProductCategory> findParents(ProductCategory productCategory,Integer count)
;

public List<ProductCategory> findChildren(ProductCategory productCategory,Integer count)
;

}