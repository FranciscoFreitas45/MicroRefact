package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.entity.ProductCategory;
public interface ProductCategoryService extends BaseService<ProductCategory, Long>{


public List<ProductCategory> findRoots(Integer count,String cacheRegion)
;

public List<ProductCategory> findTree()
;

public List<ProductCategory> findParents(ProductCategory productCategory,Integer count,String cacheRegion)
;

public List<ProductCategory> findChildren(ProductCategory productCategory,Integer count,String cacheRegion)
;

}