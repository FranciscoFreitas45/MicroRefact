package com.crontab;
 import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import com.ssc.faw.util.GenException;
public class FxOrderDaoImpl implements FxOrderDao{

 private  Logger LOG;

 private  String PACKAGE_NAME;

 private  String PROCEDURE_NAME;

 private  String PARAM_DATE_RAGE;

 private  String SCHEMA_NAME;

 private  String DB_CURSOR;

 private  JdbcCaller caller;


public void setDataSource(DataSource dataSource){
    this.caller = new JdbcCaller(dataSource);
}


@SuppressWarnings("unchecked")
@Override
public List<LegacyFxOrder> getFxOrderInRange(int dateRange){
    LOG.info("FxOrderDaoImpl.getFxOrderInRange begin ...");
    final AbstractCallableStatement cs = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName(PROCEDURE_NAME).withSchemaName(SCHEMA_NAME);
    try {
        cs.addDeclareParameter(new SqlInParameter(PARAM_DATE_RAGE, OracleTypes.INTEGER, dateRange));
        cs.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryLegacyFxOrderResultSetHandler()));
        final Map<String, Object> resultMap = caller.execute(cs);
        return (List<LegacyFxOrder>) resultMap.get(DB_CURSOR);
    } catch (SQLException e) {
        LOG.error("failed to get order by date range", e);
        throw new GenException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    } finally {
        LOG.info("FxOrderDaoImpl.getFxOrderInRange end ...");
    }
}


}