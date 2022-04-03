package com.wxcrm.Interface;
public interface IJdbcDao {

   public int[] batchUpdate(String sql,List<Object[]> batchArgs);
   public int delete(String sql,Object[] args);
   public void queryForPage(Page page);
   public List<Map<String,Object>> queryForList(String sql,Object[] args);
}