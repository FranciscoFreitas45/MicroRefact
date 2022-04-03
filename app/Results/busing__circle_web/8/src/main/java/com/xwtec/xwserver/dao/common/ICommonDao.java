package com.xwtec.xwserver.dao.common;
 import java.util.List;
import java.util.Map;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface ICommonDao {


public Map<String,Object> execProcedure(String name,Map<String,Object> params)
;

public T queryForObject(String sql,Class<T> clazz)
;

public int queryForInt(String sql,Map<String,?> paramMap)
;

public Map<String,Object> queryForMap(String sql,Map<String,?> paramMap)
;

public List<T> queryForList(String selectSql,Map<String,?> paramMap,Page page,Class<T> clazz)
;

public int[] batchUpdate(String sql,List<Map<String,?>> paramListMap)
;

public List<Long> querySequenceNextValues(String sequenceName,int size)
;

public int getLastId(String tableName,String idColume)
;

public int update(String sql,Map<String,?> paramMap)
;

public long querySequenceNextValue(String sequenceName)
;

}