package com.crontab;
 import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
public class AbstractCallableStatement {

 private  boolean compiled;

 private  String procedureName;

 private  String schemaName;

 private  String catalogName;

 private  List<SqlParameter> declaredParameters;

 protected  String callString;


public boolean isCompiled(){
    return compiled;
}


public String getSql(){
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotBlank(schemaName)) {
        sb.append(schemaName).append(".");
    }
    if (StringUtils.isNotBlank(catalogName)) {
        sb.append(catalogName).append(".");
    }
    if (StringUtils.isNotBlank(procedureName)) {
        sb.append(procedureName);
    }
    return sb.toString();
}


public AbstractCallableStatement withCatalogName(String catalogName){
    this.catalogName = catalogName;
    return this;
}


public AbstractCallableStatement withProcedureName(String procedureName){
    this.procedureName = procedureName;
    return this;
}


public AbstractCallableStatement withSchemaName(String schemaName){
    this.schemaName = schemaName;
    return this;
}


public void addDeclareParameter(SqlParameter param){
    if (isCompiled()) {
        throw new SQLException("Cannot add parameters once the query is compiled");
    }
    this.declaredParameters.add(param);
}


public List<SqlParameter> getDeclaredParameters(){
    return this.declaredParameters;
}


public void compile(){
    if (!isCompiled()) {
        if (StringUtils.isBlank(getSql())) {
            throw new SQLException("Property 'procedureName' is required");
        }
        compileInternal();
        this.compiled = true;
    }
}


public void compileInternal()


public String getCallString(){
    if (!isCompiled()) {
        compile();
    }
    return callString;
}


}