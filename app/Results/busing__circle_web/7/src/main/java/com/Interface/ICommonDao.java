package com.Interface;
public interface ICommonDao {

   public int update(String sql,Map<String,?> paramMap);
   public int queryForInt(String sql,Map<String,?> paramMap);
   public List<T> queryForList(String selectSql,Map<String,?> paramMap,Page page,Class<T> clazz);
}