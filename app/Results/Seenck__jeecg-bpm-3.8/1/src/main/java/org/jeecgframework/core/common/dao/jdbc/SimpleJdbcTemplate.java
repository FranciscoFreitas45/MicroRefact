package org.jeecgframework.core.common.dao.jdbc;
 import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.util.Assert;
@SuppressWarnings("unchecked")
public class SimpleJdbcTemplate extends JdbcTemplate{

 protected  Log logger;

 protected  JdbcTemplate jdbcTemplate;

 protected  NamedParameterJdbcTemplate namedJdbcTemplate;

 protected  SimpleJdbcInsert simpleJdbcInsert;

public SimpleJdbcTemplate(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
}
public List<Map<String,Object>> findForListMap(String sql,Map parameters){
    try {
        Assert.hasText(sql, "sql语句不正确!");
        if (parameters != null) {
            // update--begin--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
            // return jdbcTemplate.queryForList(sql, parameters);
            return namedJdbcTemplate.queryForList(sql, parameters);
        // update--end--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
        } else {
            return jdbcTemplate.queryForList(sql);
        }
    } catch (Exception e) {
        return null;
    }
}


public int executeForObject(String sql,Object bean){
    Assert.hasText(sql, "sql语句不正确!");
    if (bean != null) {
        // update--begin--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
        // return jdbcTemplate.update(sql, paramBeanMapper(bean));
        return namedJdbcTemplate.update(sql, paramBeanMapper(bean));
    // update--end--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
    } else {
        return jdbcTemplate.update(sql);
    }
}


public BeanPropertySqlParameterSource paramBeanMapper(Object object){
    return new BeanPropertySqlParameterSource(object);
}


public Object findForObject(String sql,Class clazz,Map parameters){
    try {
        Assert.hasText(sql, "sql语句不正确!");
        Assert.notNull(clazz, "集合中对象类型不能为空!");
        if (parameters != null) {
            // update--begin--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
            // return jdbcTemplate.queryForObject(sql, resultBeanMapper(clazz), parameters);
            return namedJdbcTemplate.queryForObject(sql, parameters, resultBeanMapper(clazz));
        // update--end--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
        } else {
            // -- update-begin author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本
            return jdbcTemplate.queryForObject(sql, resultBeanMapper(clazz), Long.class);
        // -- update-end author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本
        }
    } catch (Exception e) {
        return null;
    }
}


public Map findForMap(String sql,Map parameters){
    try {
        Assert.hasText(sql, "sql语句不正确!");
        if (parameters != null) {
            // update--begin--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
            // return jdbcTemplate.queryForMap(sql, parameters);
            return namedJdbcTemplate.queryForMap(sql, parameters);
        // update--end--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
        } else {
            return jdbcTemplate.queryForMap(sql);
        }
    } catch (Exception e) {
        return null;
    }
}


public BeanPropertyRowMapper resultBeanMapper(Class clazz){
    return BeanPropertyRowMapper.newInstance(clazz);
}


public List find(String sql,Class clazz,Map parameters){
    try {
        Assert.hasText(sql, "sql语句不正确!");
        Assert.notNull(clazz, "集合中对象类型不能为空!");
        if (parameters != null) {
            // update--begin--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
            // return jdbcTemplate.query(sql, resultBeanMapper(clazz),parameters);
            return namedJdbcTemplate.query(sql, parameters, resultBeanMapper(clazz));
        // update--end--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
        } else {
            return jdbcTemplate.query(sql, resultBeanMapper(clazz));
        }
    } catch (Exception e) {
        return null;
    }
}


public int[] batchUpdate(String sql,List<Object[]> batch){
    int[] updateCounts = jdbcTemplate.batchUpdate(sql, batch);
    return updateCounts;
}


public long findForLong(String sql,Map parameters){
    try {
        Assert.hasText(sql, "sql语句不正确!");
        // -- update-begin author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本  SimpleJdbcTemplate的功能 使用  JdbcTemplate 与 NamedParameterJdbcTemplate 实现
        if (parameters != null) {
            return namedJdbcTemplate.queryForObject(sql, parameters, Long.class);
        } else {
            return jdbcTemplate.queryForObject(sql, Long.class);
        }
    // -- update-end author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本  SimpleJdbcTemplate的功能 使用  JdbcTemplate 与 NamedParameterJdbcTemplate 实现
    } catch (Exception e) {
        return 0;
    }
}


public int executeForMap(String sql,Map parameters){
    Assert.hasText(sql, "sql语句不正确!");
    if (parameters != null) {
        // update--begin--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
        // return jdbcTemplate.update(sql, parameters);
        return namedJdbcTemplate.update(sql, parameters);
    // update--end--author:zhoujf Date:20180615 for:TASK #2780 [Online开发]Online报表自定义sql,以及查询条件处理重构成占位符
    } else {
        return jdbcTemplate.update(sql);
    }
}


}