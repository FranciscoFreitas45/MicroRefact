package com.Interface;
public interface ICommonDao {

   public List<T> queryForList(String selectSql,Map<String,?> paramMap,Page page,Class<T> clazz);
}