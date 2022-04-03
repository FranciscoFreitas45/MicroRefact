package com.easyshopping.service;
 import java.util.Date;
import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Article;
import com.easyshopping.entity.ArticleCategory;
import com.easyshopping.entity.Tag;
public interface ArticleService extends BaseService<Article, Long>{


public long viewHits(Long id)
;

public List<Article> findList(ArticleCategory articleCategory,Date beginDate,Date endDate,Integer first,Integer count)
;

public Page<Article> findPage(ArticleCategory articleCategory,List<Tag> tags,Pageable pageable)
;

}