package com.xwtec.xwserver.util.database;
 import java.lang.reflect.Field;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.CommonUtil;
public class OracleStoreTemplate extends StoredProcedure{

 private  Map<String,Object> params;

/**
 * 重写父类构造方法
 * @param jdbcTemplate Spring的jdbcTemplate
 * @param name 存储过程名称
 */
public OracleStoreTemplate(JdbcTemplate jdbcTemplate, String name) {
    super(jdbcTemplate, name);
}
public void setInParam(String paramName,Object value,String type){
    try {
        super.declareParameter(new SqlParameter(paramName, getParamTypeInteger(type)));
        params.put(paramName, value);
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


public int getParamTypeInteger(String type){
    try {
        // TODO 存储过程中定义的参数类型与java.sql.Types中常量名并不能一一对应，此处以后还需维护，解析更多的对应关系
        // 目前只对varchar2与varchar、number与decimal的对应关系做处理，将类型名称中出现的数字移除
        // 并且要将类型名称转成全大写，才能与常量名对应
        if ("NUMBER".equals(type)) {
            type = "DECIMAL";
        }
        Field field = Types.class.getField(type.replaceAll("\\d", "").toUpperCase());
        return Integer.valueOf(field.get(null).toString());
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


public void setOutParam(String paramName,String type){
    try {
        super.declareParameter(new SqlOutParameter(paramName, getParamTypeInteger(type)));
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


public Map<String,Object> execute(){
    if (CommonUtil.isEmpty(super.getSql())) {
        return null;
    }
    super.compile();
    return super.execute(this.params);
}


public void setInOutParam(String paramName,Object value,String type){
    try {
        super.declareParameter(new SqlInOutParameter(paramName, getParamTypeInteger(type)));
        params.put(paramName, value);
    } catch (Exception e) {
        throw new SPTException(e);
    }
}


}