package com.wxcrm.Interface;
public interface IJdbcDao {

   public void queryForPage(Page page);
   public int queryForInt(String sql,Object[] args);
   public int[] batchUpdate(String sql,List<Object[]> batchArgs);
   public List<Map<String,Object>> queryForList(String sql,Object[] args);
   public int update(String sql,Object[] args);
   public int delete(String sql,Object[] args);
}