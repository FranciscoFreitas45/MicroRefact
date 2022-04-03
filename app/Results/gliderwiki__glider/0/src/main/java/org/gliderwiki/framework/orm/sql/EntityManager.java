package org.gliderwiki.framework.orm.sql;
 import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.gliderwiki.framework.orm.sql.source.DeleteSqlSource;
import org.gliderwiki.framework.orm.sql.source.InsertSqlSource;
import org.gliderwiki.framework.orm.sql.source.SelectCountSqlSource;
import org.gliderwiki.framework.orm.sql.source.SelectListSqlSource;
import org.gliderwiki.framework.orm.sql.source.SelectOneSqlSource;
import org.gliderwiki.framework.orm.sql.source.UpdateSqlSource;
import org.gliderwiki.framework.orm.sql.util.Search;
import org.springframework.beans.BeanUtils;
import org.gliderwiki.DTO.Search;
public class EntityManager {

 private  String PREFIX_INSERT;

 private  String PREFIX_DELETE;

 private  String PREFIX_UPDATE;

 private  String PREFIX_LOAD;

 private  String PREFIX_LIST;

 private  String PREFIX_COUNT;

 private  Logger logger;

 private  SqlSession sqlSession;

 private  Configuration configuration;

 private  SqlSourceBuilder sqlSourceParser;

public EntityManager(SqlSession sqlSession) {
    super();
    this.sqlSession = sqlSession;
    this.configuration = sqlSession.getConfiguration();
    this.sqlSourceParser = new SqlSourceBuilder(configuration);
}
public T getDao(Class<T> clazz){
    return getSqlSession().getMapper(clazz);
}


public Object load(Object object){
    Class<?> clazz = object.getClass();
    String statementName = PREFIX_LOAD + clazz.getSimpleName();
    if (!configuration.hasStatement(statementName)) {
        addMappedStatement(statementName, new SelectOneSqlSource(sqlSourceParser, clazz), SqlCommandType.SELECT, clazz);
    }
    Object result = getSqlSession().selectOne(statementName, object);
    if (result != null)
        BeanUtils.copyProperties(result, object);
    return result;
}


public SqlSession getSqlSession(){
    return sqlSession;
}


public int count(Object object){
    Class<?> clazz = object.getClass();
    String statementName = PREFIX_COUNT + clazz.getSimpleName();
    if (!configuration.hasStatement(statementName)) {
        addMappedStatement(statementName, new SelectCountSqlSource(sqlSourceParser, clazz), SqlCommandType.SELECT, Integer.class);
    }
    Object result = getSqlSession().selectOne(statementName, object);
    return (Integer) result;
}


public void addMappedStatement(String statementName,SqlSource sqlSource,SqlCommandType sqlCommandType,Class<?> resultType){
    if (!configuration.hasStatement(statementName)) {
        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, statementName, sqlSource, sqlCommandType);
        statementBuilder.timeout(configuration.getDefaultStatementTimeout());
        if (resultType != null) {
            List<ResultMap> resultMaps = new ArrayList<ResultMap>();
            ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(configuration, statementBuilder.id() + "-Inline", resultType, new ArrayList<ResultMapping>());
            resultMaps.add(inlineResultMapBuilder.build());
            statementBuilder.resultMaps(resultMaps);
        }
        MappedStatement statement = statementBuilder.build();
        configuration.addMappedStatement(statement);
    }
}


public void insert(Object object){
    Class<?> clazz = object.getClass();
    String statementName = PREFIX_INSERT + clazz.getSimpleName();
    if (!configuration.hasStatement(statementName)) {
        addMappedStatement(statementName, new InsertSqlSource(sqlSourceParser, clazz), SqlCommandType.INSERT, null);
    }
    getSqlSession().insert(statementName, object);
}


public int update(Object object){
    Class<?> clazz = object.getClass();
    String statementName = PREFIX_UPDATE + clazz.getSimpleName();
    if (!configuration.hasStatement(statementName)) {
        addMappedStatement(statementName, new UpdateSqlSource(sqlSourceParser, clazz), SqlCommandType.UPDATE, null);
    }
    int result = 0;
    result = getSqlSession().update(statementName, object);
    return result;
}


public List<?> list(Object object,String orderQuery){
    Class<?> clazz = object.getClass();
    String statementName = PREFIX_LIST + clazz.getSimpleName();
    if (!configuration.hasStatement(statementName)) {
        addMappedStatement(statementName, new SelectListSqlSource(sqlSourceParser, clazz), SqlCommandType.SELECT, clazz);
    }
    Search search = new Search();
    search.setParameter(object);
    search.setOrderQuery(orderQuery);
    List list = getSqlSession().selectList(statementName, search);
    return list;
}


public int delete(Object object){
    Class<?> clazz = object.getClass();
    String statementName = PREFIX_DELETE + clazz.getSimpleName();
    if (!configuration.hasStatement(statementName)) {
        addMappedStatement(statementName, new DeleteSqlSource(sqlSourceParser, clazz), SqlCommandType.DELETE, Integer.class);
    }
    int result = 0;
    result = getSqlSession().delete(statementName, object);
    return result;
}


}