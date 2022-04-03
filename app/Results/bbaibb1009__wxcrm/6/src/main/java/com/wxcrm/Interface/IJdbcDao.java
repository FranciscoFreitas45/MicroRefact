package com.wxcrm.Interface;
public interface IJdbcDao {

   public List<Map<String,Object>> queryForList(String sql,Object[] args);
   public int delete(String sql,Object[] args);
   public void queryForPage(Page page);
   public int update(String sql,Object[] args);
}