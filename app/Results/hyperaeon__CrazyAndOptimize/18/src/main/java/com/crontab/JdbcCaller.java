package com.crontab;
 import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
public class JdbcCaller {

 private  Logger LOGGER;

 private  DataSource ds;

public JdbcCaller(DataSource ds) {
    this.ds = ds;
}
public Map<String,Object> extractOutputParameters(CallableStatement cs,List<SqlParameter> paramList){
    Map<String, Object> returnedResults = new HashMap<String, Object>();
    int sqlColIndex = 1;
    for (SqlParameter param : paramList) {
        if (param instanceof SqlOutParameter) {
            SqlOutParameter outParam = (SqlOutParameter) param;
            Object out = cs.getObject(sqlColIndex);
            if (out instanceof ResultSet) {
                if (outParam.isResultSetSupported()) {
                    returnedResults.putAll(processResultSet((ResultSet) out, outParam));
                } else {
                    throw new SQLException("SqlOutParameter does not support resultset");
                }
            } else {
                returnedResults.put(outParam.getName(), out);
            }
        }
        sqlColIndex++;
    }
    return returnedResults;
}


public void rethrow(SQLException cause,String sql,Object params){
    String causeMessage = cause.getMessage();
    if (causeMessage == null) {
        causeMessage = "";
    }
    StringBuilder msg = new StringBuilder(causeMessage);
    msg.append(" Query: ");
    msg.append(sql);
    msg.append(" Parameters: ");
    if (params == null) {
        msg.append("[]");
    } else {
        msg.append(Arrays.deepToString(params));
    }
    SQLException e = new SQLException(msg.toString(), cause.getSQLState(), cause.getErrorCode());
    e.setNextException(cause);
    throw e;
}


public CallableStatement prepareCallableStatement(CallableStatement cs,List<SqlParameter> paramList){
    CallableStatement csToUse = cs;
    int sqlColIndx = 1;
    for (SqlParameter declaredParam : paramList) {
        if (declaredParam instanceof SqlOutParameter) {
            csToUse.registerOutParameter(sqlColIndx, declaredParam.getSqlType());
        } else {
            SqlInParameter inParameter = (SqlInParameter) declaredParam;
            DBUtils.setParameterValue(csToUse, sqlColIndx, inParameter.getSqlType(), inParameter.getValue());
        }
        sqlColIndx++;
    }
    return cs;
}


public Map<String,Object> processResultSet(ResultSet rs,SqlOutParameter param){
    if (rs == null) {
        return Collections.emptyMap();
    }
    Map<String, Object> returnedResults = new HashMap<String, Object>();
    try {
        ResultSet rsToUse = rs;
        Object result = param.getResultSetHandler().handle(rsToUse);
        returnedResults.put(param.getName(), result);
    } finally {
        DBUtils.closeQuietly(rs);
    }
    return returnedResults;
}


public Map<String,Object> execute(AbstractCallableStatement procedure){
    if (procedure == null) {
        throw new SQLException("StoredProcedure is null.");
    }
    Connection conn = DBUtils.getConnection(ds);
    CallableStatement cs = null;
    Map<String, Object> returnedResults = null;
    try {
        cs = conn.prepareCall(procedure.getCallString());
        CallableStatement csToUse = this.prepareCallableStatement(cs, procedure.getDeclaredParameters());
        csToUse.execute();
        returnedResults = extractOutputParameters(csToUse, procedure.getDeclaredParameters());
    } catch (SQLException e) {
        this.rethrow(e, procedure.getCallString(), procedure.getDeclaredParameters());
    } finally {
        DBUtils.closeQuietly(cs);
        DBUtils.closeQuietly(conn);
    }
    return returnedResults;
}


public List<Map<String,Object>> transactionExcute(List<AbstractCallableStatement> procedures){
    List<Map<String, Object>> resultMaps = new ArrayList<Map<String, Object>>();
    Connection conn = DBUtils.getConnection(ds);
    conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    conn.setAutoCommit(false);
    try {
        for (AbstractCallableStatement sp : procedures) {
            Map<String, Object> resultMap = executeNotCloseConn(sp, conn);
            resultMaps.add(resultMap);
        }
        conn.commit();
    } catch (SQLException e) {
        conn.rollback();
        LOGGER.info(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    } finally {
        DBUtils.closeQuietly(conn);
    }
    return resultMaps;
}


public Map<String,Object> executeNotCloseConn(AbstractCallableStatement procedure,Connection conn){
    if (procedure == null) {
        throw new SQLException("StoredProcedure is null.");
    }
    CallableStatement cs = null;
    Map<String, Object> returnedResults = null;
    try {
        cs = conn.prepareCall(procedure.getCallString());
        CallableStatement csToUse = this.prepareCallableStatement(cs, procedure.getDeclaredParameters());
        csToUse.execute();
        returnedResults = extractOutputParameters(csToUse, procedure.getDeclaredParameters());
    } catch (SQLException e) {
        this.rethrow(e, procedure.getCallString(), procedure.getDeclaredParameters());
    } finally {
        DBUtils.closeQuietly(cs);
    }
    return returnedResults;
}


}