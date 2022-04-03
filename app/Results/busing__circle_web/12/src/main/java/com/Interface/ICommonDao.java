package com.Interface;
public interface ICommonDao {

   public List<T> queryForList(String selectSql,Map<String,?> paramMap,Page page,Class<T> clazz);
   public T queryForObject(String sql,Class<T> clazz);
   public int update(String sql,Map<String,?> paramMap);
   public int getLastId(String tableName,String idColume);
}