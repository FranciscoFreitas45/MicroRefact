package com.Interface;
public interface ICommonDao {

   public T queryForObject(String sql,Class<T> clazz);
   public List<T> queryForList(String selectSql,Map<String,?> paramMap,Page page,Class<T> clazz);
}