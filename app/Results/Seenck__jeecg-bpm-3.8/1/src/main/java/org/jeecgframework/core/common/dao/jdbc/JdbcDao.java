package org.jeecgframework.core.common.dao.jdbc;
 import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.poi.ss.formula.functions.T;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
@Repository("jdbcDao")
public class JdbcDao extends SimpleJdbcTemplate{

 public  String DATABSE_TYPE_MYSQL;

 public  String DATABSE_TYPE_POSTGRE;

 public  String DATABSE_TYPE_ORACLE;

 public  String DATABSE_TYPE_SQLSERVER;

 public  String MYSQL_SQL;

 public  String POSTGRE_SQL;

 public  String ORACLE_SQL;

 public  String SQLSERVER_SQL;

@Autowired
public JdbcDao(DataSource dataSource) {
    super(dataSource);
}
public List<T> findObjForJdbc(String sql,int page,int rows,Class<T> clazz){
    List<T> rsList = new ArrayList<T>();
    // 封装分页SQL
    sql = jeecgCreatePageSql(sql, page, rows);
    List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
    T po = null;
    for (Map<String, Object> m : mapList) {
        try {
            po = clazz.newInstance();
            MyBeanUtils.copyMap2Bean_Nobig(po, m);
            rsList.add(po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return rsList;
}


public Map<String,Object> findOneForJdbc(String sql,Object objs){
    try {
        return this.jdbcTemplate.queryForMap(sql, objs);
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
}


public Object findForObject(String sql,Class clazz,Map parameters){
    return super.findForObject(sql, clazz, parameters);
}


public Integer executeSql2(String sql,List<Object> param){
    return this.jdbcTemplate.update(sql, param);
}


public Map findForMap(String sql,Map parameters){
    return super.findForMap(sql, parameters);
}


public int[] batchUpdate(String sql,List<Object[]> batch){
    return super.batchUpdate(sql, batch);
}


public long findForLong(String sql,Map parameters){
    return super.findForLong(sql, parameters);
}


public List<Map<String,Object>> findForJdbc(String sql,Object objs){
    return this.jdbcTemplate.queryForList(sql, objs);
}


public int executeForMap(String sql,Map parameters){
    return super.executeForMap(sql, parameters);
}


public List<Map<String,Object>> findForJdbcParam(String sql,int page,int rows,Object objs){
    // 封装分页SQL
    sql = jeecgCreatePageSql(sql, page, rows);
    return jdbcTemplate.queryForList(sql, objs);
}


public Long getCountForJdbcParam(String sql,Object objs){
    // -- update-begin author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
    return jdbcTemplate.queryForObject(sql, objs, Long.class);
// -- update-end author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
}


public int getAfterSelectInsertPoint(String sql){
    int selectIndex = sql.toLowerCase().indexOf("select");
    int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
    return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
}


public List<Map<String,Object>> findForListMap(String sql,Map parameters,int page,int rows){
    // 封装分页SQL
    sql = jeecgCreatePageSql(sql, page, rows);
    return super.findForListMap(sql, parameters);
}


public int executeForObject(String sql,Object bean){
    return super.executeForObject(sql, bean);
}


public Integer countByJdbc(String sql,Object param){
    // -- update-begin author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
    return this.jdbcTemplate.queryForObject(sql, param, Integer.class);
// -- update-end author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
}


public List find(String sql,Class clazz,Map parameters){
    return super.find(sql, clazz, parameters);
}


public String jeecgCreatePageSql(String sql,int page,int rows){
    int beginNum = (page - 1) * rows;
    String[] sqlParam = new String[3];
    sqlParam[0] = sql;
    sqlParam[1] = beginNum + "";
    sqlParam[2] = rows + "";
    if (ResourceUtil.getJdbcUrl().indexOf(DATABSE_TYPE_MYSQL) != -1) {
        sql = MessageFormat.format(MYSQL_SQL, sqlParam);
    } else if (ResourceUtil.getJdbcUrl().indexOf(DATABSE_TYPE_POSTGRE) != -1) {
        sql = MessageFormat.format(POSTGRE_SQL, sqlParam);
    } else {
        int beginIndex = (page - 1) * rows;
        int endIndex = beginIndex + rows;
        sqlParam[2] = Integer.toString(beginIndex);
        sqlParam[1] = Integer.toString(endIndex);
        if (ResourceUtil.getJdbcUrl().indexOf(DATABSE_TYPE_ORACLE) != -1) {
            sql = MessageFormat.format(ORACLE_SQL, sqlParam);
        } else if (ResourceUtil.getJdbcUrl().indexOf(DATABSE_TYPE_SQLSERVER) != -1) {
            sqlParam[0] = sql.substring(getAfterSelectInsertPoint(sql));
            sql = MessageFormat.format(SQLSERVER_SQL, sqlParam);
        }
    }
    return sql;
}


public Long getCountForJdbc(String sql){
    // -- update-begin author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
    return jdbcTemplate.queryForObject(sql, Long.class);
// -- update-end author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
}


public Integer executeSql(String sql,Object param){
    return this.jdbcTemplate.update(sql, param);
}


}