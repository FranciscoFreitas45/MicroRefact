package org.jeecgframework.core.util;
 import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
public class DynamicDBUtil {

 private  Logger logger;


public void closeDBkey(String dbKey){
    BasicDataSource dataSource = getDbSourceBydbKey(dbKey);
    try {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.getConnection().commit();
            dataSource.getConnection().close();
            dataSource.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


@SuppressWarnings("unchecked")
public Object findOneByHash(String dbKey,String sql,Class<T> clazz,HashMap<String,Object> data){
    Map<String, Object> map = (Map<String, Object>) findOneByHash(dbKey, sql, data);
    return ReflectHelper.setAll(clazz, map);
}


public BasicDataSource getDbSourceBydbKey(String dbKey){
    // 获取多数据源配置
    DynamicDataSourceEntity dynamicSourceEntity = ResourceUtil.getCacheDynamicDataSourceEntity(dbKey);
    // 先判断缓存中是否存在数据库链接
    BasicDataSource cacheDbSource = ResourceUtil.getCacheBasicDataSource(dbKey);
    if (cacheDbSource != null && !cacheDbSource.isClosed()) {
        logger.debug("--------getDbSourceBydbKey------------------从缓存中获取DB连接-------------------");
        return cacheDbSource;
    } else {
        BasicDataSource dataSource = getJdbcDataSource(dynamicSourceEntity);
        ResourceUtil.putCacheBasicDataSource(dbKey, dataSource);
        logger.info("--------getDbSourceBydbKey------------------创建DB数据库连接-------------------");
        return dataSource;
    }
}


public List<T> findList(String dbKey,String sql,Class<T> clazz,Object param){
    List<T> list;
    JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
    if (ArrayUtils.isEmpty(param)) {
        list = jdbcTemplate.queryForList(sql, clazz);
    } else {
        list = jdbcTemplate.queryForList(sql, clazz, param);
    }
    return list;
}


public List<T> findListByHash(String dbKey,String sql,Class<T> clazz,HashMap<String,Object> data){
    List<T> list;
    JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
    // 根据模板获取sql
    sql = FreemarkerParseFactory.parseTemplateContent(sql, data);
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    list = namedParameterJdbcTemplate.queryForList(sql, data, clazz);
    return list;
}


@SuppressWarnings("unchecked")
public Object findOne(String dbKey,String sql,Class<T> clazz,Object param){
    Map<String, Object> map = (Map<String, Object>) findOne(dbKey, sql, param);
    return ReflectHelper.setAll(clazz, map);
}


public int update(String dbKey,String sql,Object param){
    int effectCount = 0;
    JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
    if (ArrayUtils.isEmpty(param)) {
        effectCount = jdbcTemplate.update(sql);
    } else {
        effectCount = jdbcTemplate.update(sql, param);
    }
    return effectCount;
}


public List<T> findListEntrys(String dbKey,String sql,Class<T> clazz,Object param){
    List<Map<String, Object>> queryList = findList(dbKey, sql, param);
    return ReflectHelper.transList2Entrys(queryList, clazz);
}


public JdbcTemplate getJdbcTemplate(String dbKey){
    BasicDataSource dataSource = getDbSourceBydbKey(dbKey);
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    return jdbcTemplate;
}


public int updateByHash(String dbKey,String sql,HashMap<String,Object> data){
    int effectCount = 0;
    JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
    // 根据模板获取sql
    sql = FreemarkerParseFactory.parseTemplateContent(sql, data);
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    effectCount = namedParameterJdbcTemplate.update(sql, data);
    return effectCount;
}


public List<T> findListEntrysByHash(String dbKey,String sql,Class<T> clazz,HashMap<String,Object> data){
    List<Map<String, Object>> queryList = findListByHash(dbKey, sql, data);
    return ReflectHelper.transList2Entrys(queryList, clazz);
}


@Deprecated
public BasicDataSource getJdbcDataSource(DynamicDataSourceEntity dynamicSourceEntity){
    BasicDataSource dataSource = new BasicDataSource();
    String driverClassName = dynamicSourceEntity.getDriverClass();
    String url = dynamicSourceEntity.getUrl();
    String dbUser = dynamicSourceEntity.getDbUser();
    // update-start--Author:chenjin  Date:20160712 for：多数据源目前数据库密码是明文，采用加密方式存储
    // 设置数据源的时候，要重新解密
    // String dbPassword = dynamicSourceEntity.getDbPassword();
    // 解密字符串；
    String dbPassword = PasswordUtil.decrypt(dynamicSourceEntity.getDbPassword(), dynamicSourceEntity.getDbUser(), PasswordUtil.getStaticSalt());
    // update-end--Author:chenjin  Date:20160712 for：多数据源目前数据库密码是明文，采用加密方式存储
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(dbUser);
    dataSource.setPassword(dbPassword);
    return dataSource;
}


}