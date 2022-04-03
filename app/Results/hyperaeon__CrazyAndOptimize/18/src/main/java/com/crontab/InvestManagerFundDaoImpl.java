package com.crontab;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.test.InvestManagerFund;
public class InvestManagerFundDaoImpl implements InvestManagerFundDao{

 private  Logger LOGGER;

 private  String PACKAGE_NAME;

 private  String SCHEMA_NAME;

 private  String DB_CURSOR;

 private  JdbcCaller caller;


@Override
public void setLegacyOMSDataSource(DataSource dataSource){
    this.caller = new JdbcCaller(dataSource);
}


@Override
public boolean isCustIdExist(String custId){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("isCustIdExist").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("iCUST_ID", OracleTypes.VARCHAR, custId));
        sp.addDeclareParameter(new SqlOutParameter("rs", OracleTypes.VARCHAR, new ResultSetHandler<String>() {

            @Override
            public String handle(ResultSet resultSet) throws SQLException {
                String rs = null;
                while (resultSet.next()) {
                    rs = resultSet.getString("rs");
                }
                return rs;
            }
        }));
        final Map<String, Object> resultMap = caller.execute(sp);
        String rs = (String) resultMap.get("rs");
        if (Integer.valueOf(rs) > 0) {
            return true;
        }
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    return false;
}


public List<JsonImFund> queryCustIdByFund(JsonImFund jsonObj){
    LOGGER.info("Start in queryCustIdByFund DAO");
    String fund = StringUtils.isBlank(jsonObj.getFund()) ? StringUtils.EMPTY : jsonObj.getFund().toUpperCase().trim();
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("fuzzySearchByFund").withSchemaName(SCHEMA_NAME);
    fund = fund.replaceAll("%", "/%");
    try {
        sp.addDeclareParameter(new SqlInParameter("iFUND", OracleTypes.VARCHAR, fund));
        sp.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryCustIdsResultSetHandler()));
        final Map<String, Object> resultMap = caller.execute(sp);
        List<JsonImFund> imFundList = (List<JsonImFund>) resultMap.get(DB_CURSOR);
        if (CollectionUtils.isEmpty(imFundList)) {
            throw new OrderValidationException(Constants.ERROR_QUERY_NO_RESULT, Constants.RESP_LEVEL_INFO, null);
        }
        return imFundList;
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


@Override
public String isFundExist(String custName){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("queryCustIdByShortNm").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("cust_short_name", OracleTypes.VARCHAR, custName.toUpperCase()));
        sp.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new ResultSetHandler<String>() {

            @Override
            public String handle(ResultSet rs) throws SQLException {
                String custId = null;
                if (rs.getFetchSize() > 1) {
                    LOGGER.warn("There is more than 1 cust id for this fund!! ");
                }
                while (rs.next()) {
                    custId = rs.getString("cust_id");
                }
                return custId;
            }
        }));
        final Map<String, Object> resultMap = caller.execute(sp);
        return (String) resultMap.get(DB_CURSOR);
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


@Override
public String handle(ResultSet resultSet){
    String rs = null;
    while (resultSet.next()) {
        rs = resultSet.getString("rs");
    }
    return rs;
}


@Override
public List<InvestManagerFund> fuzzySearchByImAndFund(String im,String fund){
    LOGGER.info("Start in fuzzySearchByImAndFund DAO");
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("fuzzySearchByImAndFund").withSchemaName(SCHEMA_NAME);
    String imParam = im == null ? StringUtils.EMPTY : im.toUpperCase().trim();
    String fundParam = fund == null ? StringUtils.EMPTY : fund.toUpperCase().trim();
    try {
        String imReplaced = imParam.replaceAll("%", "\\\\%");
        String fundReplaced = fundParam.replaceAll("%", "\\\\%");
        sp.addDeclareParameter(new SqlInParameter("iINVEST_MANAGER", OracleTypes.VARCHAR, imReplaced));
        sp.addDeclareParameter(new SqlInParameter("iFUND", OracleTypes.VARCHAR, fundReplaced));
        sp.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryImFundResultSetHandler()));
        final Map<String, Object> resultMap = caller.execute(sp);
        return (List<InvestManagerFund>) resultMap.get(DB_CURSOR);
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


@Override
public List<String> getCurrencyList(){
    LOGGER.info("Start to get currency list DAO");
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName("PKG_NBA_FX_CURRENCY").withProcedureName("queryCurrency").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryCurrencyResultSetHandler()));
        final Map<String, Object> resultMap = caller.execute(sp);
        return (List<String>) resultMap.get(DB_CURSOR);
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


}