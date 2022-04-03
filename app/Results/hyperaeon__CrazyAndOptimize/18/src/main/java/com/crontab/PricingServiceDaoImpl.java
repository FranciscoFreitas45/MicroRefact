package com.crontab;
 import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import com.ssc.faw.util.GenException;
public class PricingServiceDaoImpl implements PricingServiceDao{

 private  Logger LOG;

 private  String PACKAGE_NAME;

 private  String SCHEMA_NAME;

 private  String DB_CURSOR;

 private  JdbcCaller caller;


@SuppressWarnings("unchecked")
@Override
public List<PricingService> loadAll(){
    LOG.info("PricingServiceDaoImpl.loadAll begin ...");
    final AbstractCallableStatement cs = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("loadAll").withSchemaName(SCHEMA_NAME);
    LOG.info("CallableStatement is created.");
    List<PricingService> serviceList = null;
    try {
        cs.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryPricingServiceResultSetHandler()));
        LOG.info("Parameter is set for CallableStatement.");
        final Map<String, Object> resultMap = caller.execute(cs);
        LOG.info("Result got from database.");
        serviceList = (List<PricingService>) resultMap.get(DB_CURSOR);
    } catch (SQLException e) {
        LOG.error(e.getMessage());
        LOG.info("PricingServiceDaoImpl.loadAll end with exception ...");
        throw new GenException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    LOG.info("PricingServiceDaoImpl.loadAll end ...");
    return serviceList;
}


public void initJdbcCall(DataSource dataSource){
    this.caller = new JdbcCaller(dataSource);
}


}