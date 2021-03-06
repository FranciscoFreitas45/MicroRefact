package org.jeecgframework.web.cgform.service.impl.config.util;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.jeecgframework.web.cgform.service.config.DbTableServiceI;
import org.jeecgframework.web.cgform.service.impl.config.DbTableMysqlHandleImpl;
import org.jeecgframework.web.cgform.service.impl.config.DbTableOracleHandleImpl;
import org.jeecgframework.web.cgform.service.impl.config.DbTablePostgresHandleImpl;
import org.jeecgframework.web.cgform.service.impl.config.DbTableServiceMysqlImpl;
import org.jeecgframework.web.cgform.service.impl.config.TableSQLServerHandleImpl;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
public class DbTableUtil {


public Map<String,Object> getColumnMap(List<Map<String,Object>> queryForList){
    Map<String, Object> columnMap = new HashMap<String, Object>();
    for (int i = 0; i < queryForList.size(); i++) {
        columnMap.put(queryForList.get(i).get("column_name").toString(), queryForList.get(i));
    }
    return columnMap;
}


public String getDataType(Session session){
    String dataType = "MYSQL";
    String dialect = ((SessionImpl) session).getFactory().getDialect().getClass().getName();
    if (dialect.equals("org.hibernate.dialect.MySQLDialect")) {
        dataType = "MYSQL";
    } else if (dialect.contains("Oracle")) {
        dataType = "ORACLE";
    } else if (dialect.equals("org.hibernate.dialect.PostgreSQLDialect")) {
        dataType = "POSTGRESQL";
    } else if (dialect.equals("org.hibernate.dialect.SQLServerDialect")) {
        dataType = "SQLSERVER";
    } else if (dialect.equals("org.jeecgframework.core.common.hibernate.dialect.MySQLServer2008Dialect")) {
        dataType = "SQLSERVER";
    }
    return dataType;
}


public String translatorToDbField(String fileName){
    // 去掉转换
    return fileName;
// String name = "";
// char[] chars = fileName.toCharArray();
// for(int i =0 ;i<chars.length;i++){
// name+= chars[i]>'A'&&chars[i]<'Z'?("_"+Character.toLowerCase(chars[i])):chars[i];
// }
// return name;
}


public DbTableHandleI getTableHandle(Session session){
    DbTableHandleI dbTableHandle = null;
    String dialect = ((SessionImpl) session).getFactory().getDialect().getClass().getName();
    if (dialect.equals("org.hibernate.dialect.MySQLDialect")) {
        dbTableHandle = new DbTableMysqlHandleImpl();
    } else if (dialect.contains("Oracle")) {
        dbTableHandle = new DbTableOracleHandleImpl();
    } else if (dialect.equals("org.hibernate.dialect.PostgreSQLDialect")) {
        dbTableHandle = new DbTablePostgresHandleImpl();
    } else if (dialect.equals("org.hibernate.dialect.SQLServerDialect")) {
        dbTableHandle = new TableSQLServerHandleImpl();
    } else if (dialect.equals("org.jeecgframework.core.common.hibernate.dialect.MySQLServer2008Dialect")) {
        dbTableHandle = new TableSQLServerHandleImpl();
    }
    return dbTableHandle;
}


public DbTableServiceI getTableUtil(Session session){
    DbTableServiceI tableUtil = null;
    String dialect = ((SessionImpl) session).getFactory().getDialect().getClass().getName();
    if (dialect.equals("org.hibernate.dialect.MySQLDialect")) {
        tableUtil = new DbTableServiceMysqlImpl();
    }
    return tableUtil;
}


}