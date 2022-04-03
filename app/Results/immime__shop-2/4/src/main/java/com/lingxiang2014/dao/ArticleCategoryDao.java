package com.lingxiang2014.dao;
 import java.util.List;
import com.lingxiang2014.entity.ArticleCategory;
public interface ArticleCategoryDao extends BaseDao<ArticleCategory, Long>{


public List<ArticleCategory> findRoots(Integer count)
;

public List<ArticleCategory> findParents(ArticleCategory articleCategory,Integer count)
;

public List<ArticleCategory> findChildren(ArticleCategory articleCategory,Integer count)
;

}