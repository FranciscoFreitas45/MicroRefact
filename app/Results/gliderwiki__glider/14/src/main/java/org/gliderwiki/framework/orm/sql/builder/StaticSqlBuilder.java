package org.gliderwiki.framework.orm.sql.builder;
 import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.log4j.Logger;
public class StaticSqlBuilder implements SqlSource{

 private  Logger logger;

 private  SqlSource sqlSource;

public StaticSqlBuilder() {
    this.sqlSource = null;
}
public BoundSql getBoundSql(Object parameterObject){
    return this.sqlSource.getBoundSql(parameterObject);
}


public void parse(SqlSourceBuilder sqlSourceParser,String sql,Class<?> clazz){
    this.sqlSource = sqlSourceParser.parse(sql, clazz);
}


}