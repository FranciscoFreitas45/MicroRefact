package com.wxcrm.common.dao;
 import java.util.List;
import java.util.Map;
import com.wxcrm.util.Page;
public interface IJdbcDao {


public int add(String sql,Object[] args)
;

public long queryForLong(String sql,Object[] args)
;

public Map<String,Object> queryForMap(String sql,Object[] args)
;

public List<Map<String,Object>> queryForList(String sql,Object[] args)
;

public int[] batchUpdate(String sql,List<Object[]> batchArgs)
;

public int update(String sql,Object[] args)
;

public void queryForPage(Page page)
;

public int delete(String sql,Object[] args)
;

public int queryForInt(String sql,Object[] args)
;

public T queryForObject(String sql,Object[] args,Class<T> requiredType)
;

public float queryForFloat(String sql,Object[] args)
;

public double queryForDouble(String sql,Object[] args)
;

public String queryForString(String sql,Object[] args)
;

}