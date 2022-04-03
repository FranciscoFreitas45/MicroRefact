package com.easyshopping.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import com.easyshopping.dao.ArticleCategoryDao;
import com.easyshopping.entity.ArticleCategory;
import com.easyshopping.service.ArticleCategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("articleCategoryServiceImpl")
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategory, Long>implements ArticleCategoryService{

@Resource(name = "articleCategoryDaoImpl")
 private  ArticleCategoryDao articleCategoryDao;


@Transactional(readOnly = true)
@Cacheable("articleCategory")
public List<ArticleCategory> findRoots(Integer count,String cacheRegion){
    return articleCategoryDao.findRoots(count);
}


@Transactional(readOnly = true)
public List<ArticleCategory> findTree(){
    return articleCategoryDao.findChildren(null, null);
}


@Resource(name = "articleCategoryDaoImpl")
public void setBaseDao(ArticleCategoryDao articleCategoryDao){
    super.setBaseDao(articleCategoryDao);
}


@Override
@Transactional
@CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
public void save(ArticleCategory articleCategory){
    super.save(articleCategory);
}


@Override
@Transactional
@CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
public ArticleCategory update(ArticleCategory articleCategory,String ignoreProperties){
    return super.update(articleCategory, ignoreProperties);
}


@Transactional(readOnly = true)
@Cacheable("articleCategory")
public List<ArticleCategory> findParents(ArticleCategory articleCategory,Integer count,String cacheRegion){
    return articleCategoryDao.findParents(articleCategory, count);
}


@Transactional(readOnly = true)
@Cacheable("articleCategory")
public List<ArticleCategory> findChildren(ArticleCategory articleCategory,Integer count,String cacheRegion){
    return articleCategoryDao.findChildren(articleCategory, count);
}


@Override
@Transactional
@CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
public void delete(ArticleCategory articleCategory){
    super.delete(articleCategory);
}


}