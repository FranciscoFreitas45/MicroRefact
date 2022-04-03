package com.wxcrm.Interface;
public interface IJdbcDao {

   public List<Map<String,Object>> queryForList(String sql,Object[] args);
   public void queryForPage(Page page);
   public Map<String,Object> queryForMap(String sql,Object[] args);
}