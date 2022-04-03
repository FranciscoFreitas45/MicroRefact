package com.easyshopping.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import com.easyshopping.dao.ProductCategoryDao;
import com.easyshopping.entity.ProductCategory;
import com.easyshopping.service.ProductCategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("productCategoryServiceImpl")
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory, Long>implements ProductCategoryService{

@Resource(name = "productCategoryDaoImpl")
 private  ProductCategoryDao productCategoryDao;


@Transactional(readOnly = true)
@Cacheable("productCategory")
public List<ProductCategory> findRoots(Integer count,String cacheRegion){
    return productCategoryDao.findRoots(count);
}


@Transactional(readOnly = true)
public List<ProductCategory> findTree(){
    return productCategoryDao.findChildren(null, null);
}


@Resource(name = "productCategoryDaoImpl")
public void setBaseDao(ProductCategoryDao productCategoryDao){
    super.setBaseDao(productCategoryDao);
}


@Override
@Transactional
@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
public void save(ProductCategory productCategory){
    super.save(productCategory);
}


@Override
@Transactional
@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
public ProductCategory update(ProductCategory productCategory,String ignoreProperties){
    return super.update(productCategory, ignoreProperties);
}


@Transactional(readOnly = true)
@Cacheable("productCategory")
public List<ProductCategory> findParents(ProductCategory productCategory,Integer count,String cacheRegion){
    return productCategoryDao.findParents(productCategory, count);
}


@Transactional(readOnly = true)
@Cacheable("productCategory")
public List<ProductCategory> findChildren(ProductCategory productCategory,Integer count,String cacheRegion){
    return productCategoryDao.findChildren(productCategory, count);
}


@Override
@Transactional
@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
public void delete(ProductCategory productCategory){
    super.delete(productCategory);
}


}