package com.lingxiang2014.dao;
 import java.util.Date;
import java.util.List;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Article;
import com.lingxiang2014.entity.ArticleCategory;
import com.lingxiang2014.entity.Tag;
public interface ArticleDao extends BaseDao<Article, Long>{


public List<Article> findList(ArticleCategory articleCategory,Date beginDate,Date endDate,Integer first,Integer count)
;

public Page<Article> findPage(ArticleCategory articleCategory,List<Tag> tags,Pageable pageable)
;

}