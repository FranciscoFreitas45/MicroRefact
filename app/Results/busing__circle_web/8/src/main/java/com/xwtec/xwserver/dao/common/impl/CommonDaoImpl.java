package com.xwtec.xwserver.dao.common.impl;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.Assert;
import com.xwtec.xwserver.util.database.OracleStoreTemplate;
@Repository
public class CommonDaoImpl extends NamedParameterJdbcDaoSupportimplements ICommonDao{


public Map<String,Object> execProcedure(String name,Map<String,Object> params){
    OracleStoreTemplate oracleStoreTemplate = new OracleStoreTemplate(super.getJdbcTemplate(), name);
    String sql = "select u.TEXT from user_source u where name=:name and type='PROCEDURE'";
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("name", name.toUpperCase());
    // 根据存储过程名，查询存储过程的定义语句，后面要根据定义语句解析存储过程的参数
    List<Map<String, Object>> procDefList = this.queryForList(sql, paramMap);
    StringBuilder procDef = new StringBuilder();
    // 参数字符串
    String paramStr = null;
    // 存储过程定义存在数据库中是多条数据，每条数据是在创建存储过程时定义的一行语句
    // 将这些语句拼接成一个完整的字符串方便解析参数
    for (Map<String, Object> procDefTemp : procDefList) {
        procDef.append(procDefTemp.get("TEXT").toString().replace("\n", ""));
    }
    // 从第一次找到存储过程名称处截断
    // (为防止存储过程定义语句再次出现与存储过程名称相同的单词，接下来会在第一次出现存储过程名称的后面寻找参数，后面再出现存储过程名称忽略)
    procDef.delete(0, procDef.indexOf(name));
    // 查询存储过程的参数
    // 正则表达式解释：现在字符串的开始处一定是存储过程名
    // 所以现在正则寻找的是从字符串开始处并且以存储过程名开始，后面跟不定个数的空格(JDK1.6在断言内使用*来匹配不定个数会报错，只能修改为定数，此处定数的数值需要足够大)，
    // 后找到"("，截取"("到第一次出现")"中间的字符串，如果从存储过程名开始后不是跟空格，找到其他字符再找到"("不算，说明存储过程没有参数
    Pattern pattern = Pattern.compile("(?<=^" + name + "\\s{0,999999}[(]).*?(?=[)])");
    Matcher matcher = pattern.matcher(procDef);
    if (matcher.find(0)) {
        paramStr = matcher.group(0);
    }
    // 参数字符串不为空存储过程才有参数
    if (paramStr != null) {
        // 开始解析参数
        String[] paramArr = paramStr.split(",");
        for (String param : paramArr) {
            // 参数的格式为：参数名在最前，参数类型在最后，中间是"IN"、"OUT"、"IN OUT"或者为空来标识当前参数是输入或输出参数
            // 参数名、输入输出类型与参数类型中间用空格分开
            // 所以以空格切分，取第一个为参数名，取最后一个为参数类型
            String[] paramArrTemp = param.split(" ");
            if (param.toUpperCase().indexOf("OUT") != -1) {
                // 输出参数
                oracleStoreTemplate.setOutParam(paramArrTemp[0], paramArrTemp[paramArrTemp.length - 1]);
            } else if (param.toUpperCase().indexOf("IN") != -1) {
                // 输入参数
                oracleStoreTemplate.setInParam(paramArrTemp[0], params.get(paramArrTemp[0]), paramArrTemp[paramArrTemp.length - 1]);
            } else if (param.toUpperCase().indexOf("IN OUT") != -1) {
                // 输入输出参数
                oracleStoreTemplate.setInOutParam(paramArrTemp[0], params.get(paramArrTemp[0]), paramArrTemp[paramArrTemp.length - 1]);
            } else {
                // 如果没有标识输入输出类型，默认为输入参数
                oracleStoreTemplate.setInParam(paramArrTemp[0], params.get(paramArrTemp[0]), paramArrTemp[paramArrTemp.length - 1]);
            }
        }
    }
    return oracleStoreTemplate.execute();
}


public Map<String,Object> queryForMap(String sql,Map<String,?> paramMap){
    try {
        return super.getNamedParameterJdbcTemplate().queryForMap(sql, paramMap);
    } catch (EmptyResultDataAccessException e) {
        return null;
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


public List<T> queryForList(String selectSql,Map<String,?> paramMap,Page page,Class<T> clazz){
    try {
        if (page != null) {
            int count = this.queryForInt(this.getCountSql(selectSql), paramMap);
            page.setTotalRow(count);
            return this.queryForList(this.getLimitSql(selectSql, page.getCurrentPage(), page.getRowsPerPage()), paramMap, clazz);
        } else {
            return this.queryForList(selectSql, paramMap, clazz);
        }
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


@SuppressWarnings("unchecked")
public int[] batchUpdate(String sql,List<Map<String,?>> paramListMap){
    try {
        return super.getNamedParameterJdbcTemplate().batchUpdate(sql, paramListMap.toArray(new HashMap[0]));
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


public List<Long> querySequenceNextValues(String sequenceName,int size){
    try {
        return this.getJdbcTemplate().query(String.format("select %s.nextval from dual connect by rownum <= ?", sequenceName), new Object[] { size }, new RowMapper<Long>() {

            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(1);
            }
        });
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


public int getLastId(String tableName,String idColume){
    String sql = "select max(" + idColume + ") from " + tableName;
    return this.queryForInt(sql, null);
}


public int update(String sql,Map<String,?> paramMap){
    try {
        return super.getNamedParameterJdbcTemplate().update(sql, paramMap);
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


@SuppressWarnings("deprecation")
public long querySequenceNextValue(String sequenceName){
    try {
        return super.getJdbcTemplate().queryForLong(String.format("select %s.nextval from dual", sequenceName));
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


public String getLimitSql(String sql,int currentPage,int pageSize){
    Assert.isTrue(currentPage > 0, "'currentPage' 不能为负数!");
    Assert.isTrue(pageSize > 0, "'pageSize' 不能为负数!");
    String s = sql + " limit " + (currentPage - 1) * pageSize + "," + pageSize;
    return s;
}


@Resource(name = "dataSource")
public void setSuperDataSource(DataSource dataSource){
    setDataSource(dataSource);
}


public T queryForObject(String sql,Class<T> clazz){
    try {
        return this.queryForObject(sql, null, clazz);
    } catch (EmptyResultDataAccessException e) {
        return null;
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


@SuppressWarnings("deprecation")
public int queryForInt(String sql,Map<String,?> paramMap){
    try {
        return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


public Long mapRow(ResultSet rs,int rowNum){
    return rs.getLong(1);
}


public String getCountSql(String sql){
    return String.format("select count(1) from (%s) as total", sql);
}


}