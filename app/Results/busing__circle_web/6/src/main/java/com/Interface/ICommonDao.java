package com.Interface;
public interface ICommonDao {

   public int[] batchUpdate(String sql,List<Map<String,?>> paramListMap);
   public List<T> queryForList(String selectSql,Map<String,?> paramMap,Page page,Class<T> clazz);
   public int update(String sql,Map<String,?> paramMap);
   public T queryForObject(String sql,Class<T> clazz);
   public int queryForInt(String sql,Map<String,?> paramMap);
}