package com.Interface;
public interface ICommonDao {

   public int update(String sql,Map<String,?> paramMap);
   public Map<String,Object> queryForMap(String sql,Map<String,?> paramMap);
}