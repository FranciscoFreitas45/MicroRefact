package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.entity.ArticleCategory;
public interface ArticleCategoryService extends BaseService<ArticleCategory, Long>{


public List<ArticleCategory> findRoots(Integer count,String cacheRegion)
;

public List<ArticleCategory> findTree()
;

public List<ArticleCategory> findParents(ArticleCategory articleCategory,Integer count,String cacheRegion)
;

public List<ArticleCategory> findChildren(ArticleCategory articleCategory,Integer count,String cacheRegion)
;

}