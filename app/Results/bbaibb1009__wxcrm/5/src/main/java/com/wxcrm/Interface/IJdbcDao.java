package com.wxcrm.Interface;
public interface IJdbcDao {

   public void queryForPage(Page page);
   public List<Map<String,Object>> queryForList(String sql,Object[] args);
}