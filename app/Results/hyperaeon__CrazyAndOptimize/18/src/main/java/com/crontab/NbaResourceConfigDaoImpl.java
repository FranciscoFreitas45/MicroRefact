package com.crontab;
 import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
public class NbaResourceConfigDaoImpl implements NbaResourceConfigDao{

 private  Logger LOGGER;

 private  String PACKAGE_NAME;

 private  String SCHEMA_NAME;

 private  String PROCEDURE_SELECT_CONFIG;

 private  String PROCEDURE_INSERT_CONFIG;

 private  String PROCEDURE_UPDATE_CONFIG;

 private  String PROCEDURE_DELETE_CONFIG;

 private  String PARAM_HOST;

 private  String PARAM_MODULE;

 private  String PARAM_KEY;

 private  String PARAM_VALUE;

 private  String PARAM_UPDATE_DATE;

 private  String PARAM_UPDATE_USER;

 private  String PARAM_CURSOR_RESULT;

 private  JdbcCaller caller;


public void deleteResourceConfigByKey(String host,String module,String key){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName(PROCEDURE_DELETE_CONFIG).withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(newSqlParam(PARAM_HOST, OracleTypes.VARCHAR, host));
        sp.addDeclareParameter(newSqlParam(PARAM_MODULE, OracleTypes.VARCHAR, module));
        sp.addDeclareParameter(newSqlParam(PARAM_KEY, OracleTypes.VARCHAR, key));
        caller.execute(sp);
    } catch (SQLException e) {
        LOGGER.error("delete resource config failed", e);
        throw new DatabaseAccessException("database exception raised", e);
    }
}


public NbaResourceConfig queryResourceConfigByKey(String host,String module,String key){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName(PROCEDURE_SELECT_CONFIG).withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(newSqlParam(PARAM_HOST, OracleTypes.VARCHAR, host));
        sp.addDeclareParameter(newSqlParam(PARAM_MODULE, OracleTypes.VARCHAR, module));
        sp.addDeclareParameter(newSqlParam(PARAM_KEY, OracleTypes.VARCHAR, key));
        sp.addDeclareParameter(new SqlOutParameter(PARAM_CURSOR_RESULT, OracleTypes.CURSOR, new ResultSetHandler<NbaResourceConfig>() {

            @Override
            public NbaResourceConfig handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    NbaResourceConfig cfg = new NbaResourceConfig();
                    cfg.setHost(rs.getString("HOST"));
                    cfg.setModule(rs.getString("MODULE"));
                    cfg.setKey(rs.getString("KEY"));
                    cfg.setValue(rs.getString("VALUE"));
                    cfg.setUpdateDate(rs.getTimestamp("UPDATED_DATETIME"));
                    cfg.setUpdateBy(rs.getString("UPDATED_BY"));
                    return cfg;
                }
                return null;
            }
        }));
        final Map<String, Object> resultMap = caller.execute(sp);
        return (NbaResourceConfig) resultMap.get(PARAM_CURSOR_RESULT);
    } catch (SQLException e) {
        LOGGER.error("delete resource config failed", e);
        throw new DatabaseAccessException("database exception raised", e);
    }
}


public void updateResourceConfig(NbaResourceConfig config){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName(PROCEDURE_UPDATE_CONFIG).withSchemaName(SCHEMA_NAME);
    Date updateDate = config.getUpdateDate() == null ? new Date() : config.getUpdateDate();
    try {
        sp.addDeclareParameter(newSqlParam(PARAM_HOST, OracleTypes.VARCHAR, config.getHost()));
        sp.addDeclareParameter(newSqlParam(PARAM_MODULE, OracleTypes.VARCHAR, config.getModule()));
        sp.addDeclareParameter(newSqlParam(PARAM_KEY, OracleTypes.VARCHAR, config.getKey()));
        sp.addDeclareParameter(newSqlParam(PARAM_VALUE, OracleTypes.VARCHAR, config.getValue()));
        sp.addDeclareParameter(newSqlParam(PARAM_UPDATE_DATE, OracleTypes.TIMESTAMP, new java.sql.Timestamp(updateDate.getTime())));
        sp.addDeclareParameter(newSqlParam(PARAM_UPDATE_USER, OracleTypes.VARCHAR, config.getUpdateBy()));
        caller.execute(sp);
    } catch (SQLException e) {
        LOGGER.error("update resource config failed", e);
        throw new DatabaseAccessException("database exception raised", e);
    }
}


public void insertResourceConfig(NbaResourceConfig config){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName(PROCEDURE_INSERT_CONFIG).withSchemaName(SCHEMA_NAME);
    Date updateDate = config.getUpdateDate() == null ? new Date() : config.getUpdateDate();
    try {
        sp.addDeclareParameter(newSqlParam(PARAM_HOST, OracleTypes.VARCHAR, config.getHost()));
        sp.addDeclareParameter(newSqlParam(PARAM_MODULE, OracleTypes.VARCHAR, config.getModule()));
        sp.addDeclareParameter(newSqlParam(PARAM_KEY, OracleTypes.VARCHAR, config.getKey()));
        sp.addDeclareParameter(newSqlParam(PARAM_VALUE, OracleTypes.VARCHAR, config.getValue()));
        sp.addDeclareParameter(newSqlParam(PARAM_UPDATE_DATE, OracleTypes.TIMESTAMP, new java.sql.Timestamp(updateDate.getTime())));
        sp.addDeclareParameter(newSqlParam(PARAM_UPDATE_USER, OracleTypes.VARCHAR, config.getUpdateBy()));
        caller.execute(sp);
    } catch (SQLException e) {
        LOGGER.error("insert resource config failed", e);
        throw new DatabaseAccessException("database exception raised", e);
    }
}


public void setDataSource(DataSource ds){
    this.caller = new JdbcCaller(ds);
}


@Override
public NbaResourceConfig handle(ResultSet rs){
    if (rs.next()) {
        NbaResourceConfig cfg = new NbaResourceConfig();
        cfg.setHost(rs.getString("HOST"));
        cfg.setModule(rs.getString("MODULE"));
        cfg.setKey(rs.getString("KEY"));
        cfg.setValue(rs.getString("VALUE"));
        cfg.setUpdateDate(rs.getTimestamp("UPDATED_DATETIME"));
        cfg.setUpdateBy(rs.getString("UPDATED_BY"));
        return cfg;
    }
    return null;
}


public SqlInParameter newSqlParam(String name,int sqlType,Object value){
    return new SqlInParameter(name, sqlType, value);
}


}