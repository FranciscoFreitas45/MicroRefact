package org.jeecgframework.core.util;
 import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
public class SqlUtil {

 public  String DATABSE_TYPE_MYSQL;

 public  String DATABSE_TYPE_POSTGRE;

 public  String DATABSE_TYPE_ORACLE;

 public  String DATABSE_TYPE_SQLSERVER;

 public  String MYSQL_SQL;

 public  String POSTGRE_SQL;

 public  String ORACLE_SQL;

 public  String SQLSERVER_SQL;

 public  String MYSQL_ALLTABLES_SQL;

 public  String POSTGRE__ALLTABLES_SQL;

 public  String ORACLE__ALLTABLES_SQL;

 public  String SQLSERVER__ALLTABLES_SQL;

 public  String MYSQL_ALLCOLUMNS_SQL;

 public  String POSTGRE_ALLCOLUMNS_SQL;

 public  String ORACLE_ALLCOLUMNS_SQL;

 public  String SQLSERVER_ALLCOLUMNS_SQL;


public String getCountSql(String sql,Map params){
    String querySql = getFullSql(sql, params);
    querySql = "SELECT COUNT(*) FROM (" + querySql + ") t2";
    return querySql;
}


public String getAllTableSql(String dbType,String param){
    if (StringUtil.isNotEmpty(dbType)) {
        if (dbType.equals(DATABSE_TYPE_MYSQL)) {
            return MessageFormat.format(MYSQL_ALLTABLES_SQL, param);
        } else if (dbType.equals(DATABSE_TYPE_ORACLE)) {
            return ORACLE__ALLTABLES_SQL;
        } else if (dbType.equals(DATABSE_TYPE_POSTGRE)) {
            return POSTGRE__ALLTABLES_SQL;
        } else if (dbType.equals(DATABSE_TYPE_SQLSERVER)) {
            return SQLSERVER__ALLTABLES_SQL;
        }
    }
    return null;
}


@SuppressWarnings("rawtypes")
public String jeecgCreatePageSql(String dbKey,String sql,Map params,int page,int rows){
    // update-begin--Author:zhoujf  Date:20180330 for：TASK #2585 【问题确认】online报表问题确认 其它数据源的时候，传进来的查询条件参数不会进行拼接
    sql = getFullSql(sql, params);
    // update-begin--Author:zhoujf  Date:20180330 for：TASK #2585 【问题确认】online报表问题确认 其它数据源的时候，传进来的查询条件参数不会进行拼接
    int beginNum = (page - 1) * rows;
    String[] sqlParam = new String[3];
    sqlParam[0] = sql;
    sqlParam[1] = beginNum + "";
    sqlParam[2] = rows + "";
    DynamicDataSourceEntity dynamicSourceEntity = ResourceUtil.getCacheDynamicDataSourceEntity(dbKey);
    String databaseType = dynamicSourceEntity.getDbType();
    if (DATABSE_TYPE_MYSQL.equalsIgnoreCase(databaseType)) {
        sql = MessageFormat.format(MYSQL_SQL, sqlParam);
    } else if (DATABSE_TYPE_POSTGRE.equalsIgnoreCase(databaseType)) {
        sql = MessageFormat.format(POSTGRE_SQL, sqlParam);
    } else {
        int beginIndex = (page - 1) * rows;
        int endIndex = beginIndex + rows;
        sqlParam[2] = Integer.toString(beginIndex);
        sqlParam[1] = Integer.toString(endIndex);
        if (DATABSE_TYPE_ORACLE.equalsIgnoreCase(databaseType)) {
            sql = MessageFormat.format(ORACLE_SQL, sqlParam);
        } else if (DATABSE_TYPE_SQLSERVER.equalsIgnoreCase(databaseType)) {
            sqlParam[0] = sql.substring(getAfterSelectInsertPoint(sql));
            sql = MessageFormat.format(SQLSERVER_SQL, sqlParam);
        }
    }
    return sql;
}


public int getAfterSelectInsertPoint(String sql){
    int selectIndex = sql.toLowerCase().indexOf("select");
    int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
    return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
}


public String getAllCloumnSql(String dbType,String param){
    if (StringUtil.isNotEmpty(dbType)) {
        if (dbType.equals(DATABSE_TYPE_MYSQL)) {
            return MessageFormat.format(MYSQL_ALLCOLUMNS_SQL, param);
        } else if (dbType.equals(DATABSE_TYPE_ORACLE)) {
            return MessageFormat.format(ORACLE_ALLCOLUMNS_SQL, param);
        } else if (dbType.equals(DATABSE_TYPE_POSTGRE)) {
            return MessageFormat.format(POSTGRE_ALLCOLUMNS_SQL, param);
        } else if (dbType.equals(DATABSE_TYPE_SQLSERVER)) {
            return MessageFormat.format(SQLSERVER_ALLCOLUMNS_SQL, param);
        }
    }
    return null;
}


public String getFullSql(String sql,Map params){
    StringBuilder sqlB = new StringBuilder();
    sqlB.append("SELECT t.* FROM ( ");
    sqlB.append(sql + " ");
    sqlB.append(") t ");
    if (params != null && params.size() >= 1) {
        sqlB.append("WHERE 1=1  ");
        Iterator it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            String value = String.valueOf(params.get(key));
            if (!StringUtil.isEmpty(value) && !"null".equals(value)) {
                sqlB.append(" AND ");
                sqlB.append(" " + key + value);
            }
        }
    }
    return sqlB.toString();
}


}