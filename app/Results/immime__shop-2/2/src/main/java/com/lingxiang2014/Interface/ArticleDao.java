package com.lingxiang2014.Interface;
public interface ArticleDao {

   public List<Article> findList(ArticleCategory articleCategory,Date beginDate,Date endDate,Integer first,Integer count);
   public Object clear(Object Object);
   public Object count(Object Object);
}