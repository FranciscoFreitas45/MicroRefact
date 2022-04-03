package com.ipe.common.dao;
 import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class SpringJdbcDao {

@Autowired
 private  JdbcTemplate jdbcTemplate;


public Map<String,Object> queryMap(String sql,List<Object> conditions){
    return queryForMap(sql, conditions);
}


public long queryForLong(String sql,List<Object> conditions){
    if (conditions != null && conditions.size() > 0) {
        return jdbcTemplate.queryForObject(sql, Long.class, conditions);
    }
    return jdbcTemplate.queryForObject(sql, Long.class);
}


public Map<String,Object> queryForMap(String sql,List<Object> conditions){
    if (conditions != null && conditions.size() > 0) {
        return jdbcTemplate.queryForMap(sql, conditions.toArray());
    }
    return jdbcTemplate.queryForMap(sql);
}


public List<?> queryForList(String sql,List<Object> conditions,Class<?> elementType){
    if (conditions != null && conditions.size() > 0) {
        return jdbcTemplate.queryForList(sql, conditions.toArray(), elementType);
    }
    return jdbcTemplate.queryForList(sql, elementType);
}


public List<Map<String,Object>> queryListMap(String sql,List<Object> conditions){
    return queryForListMap(sql, conditions);
}


public List<Map<String,Object>> queryForListMap(String sql,List<Object> conditions){
    if (conditions != null && conditions.size() > 0) {
        return jdbcTemplate.queryForList(sql, conditions.toArray());
    }
    return jdbcTemplate.queryForList(sql);
}


public long queryLong(String sql,List<Object> conditions){
    return queryForLong(sql, conditions);
}


public List<?> queryList(String sql,List<Object> conditions,Class<?> elementType){
    return queryForList(sql, conditions, elementType);
}


}