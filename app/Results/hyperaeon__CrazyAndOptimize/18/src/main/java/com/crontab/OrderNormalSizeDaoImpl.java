package com.crontab;
 import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import com.ssc.faw.util.GenException;
import com.test.CurrencyPairGroup;
public class OrderNormalSizeDaoImpl implements OrderNormalSizeDao{

 private  Logger LOGGER;

 private  String PACKAGE_NAME;

 private  String SCHEMA_NAME;

 private  JdbcCaller caller;


@SuppressWarnings("unchecked")
@Override
public Map<String,BigDecimal> queryDefaultValue(String validationId){
    LOGGER.info("queryDefaultValue begins...");
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("queryDefaultValue").withSchemaName(SCHEMA_NAME);
    Map<String, BigDecimal> map = null;
    try {
        sp.addDeclareParameter(new SqlInParameter("I_VALIDATION_ID", OracleTypes.VARCHAR, validationId));
        sp.addDeclareParameter(new SqlOutParameter("O_CURSOR", OracleTypes.CURSOR, new ResultSetHandler<Map<String, BigDecimal>>() {

            @Override
            public Map<String, BigDecimal> handle(ResultSet rs) throws SQLException {
                Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
                while (rs.next()) {
                    resultMap.put(rs.getString("PRICING_SERVICE_ID"), rs.getBigDecimal("VALUE"));
                }
                return resultMap;
            }
        }));
        final Map<String, Object> resultMap = caller.execute(sp);
        map = (Map<String, BigDecimal>) resultMap.get("O_CURSOR");
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        LOGGER.info("queryDefaultValue ends with exception...");
        throw new GenException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    LOGGER.info("queryDefaultValue ends...");
    return map;
}


@Override
public void deleteOrderNormalSize(String productCategoryId,String customerId,String ccy1,String ccy2){
    LOGGER.info("deleteOrderNormalSize begins...");
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("deleteOrderNormalSize").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("I_PRODUCT_CATEGORY_ID", OracleTypes.VARCHAR, productCategoryId));
        sp.addDeclareParameter(new SqlInParameter("I_CUSTOMER_ID", OracleTypes.VARCHAR, customerId));
        sp.addDeclareParameter(new SqlInParameter("I_CCY1", OracleTypes.VARCHAR, ccy1));
        sp.addDeclareParameter(new SqlInParameter("I_CCY2", OracleTypes.VARCHAR, ccy2));
        caller.execute(sp);
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        LOGGER.info("deleteOrderNormalSize ends with exception...");
        throw new GenException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    LOGGER.info("deleteOrderNormalSize ends...");
}


@Override
public void saveCurrencyPairAndNormalSize(JsonOrderNormalSize normalSize,String user){
    LOGGER.info("saveCurrencyPairAndNormalSize begins...");
    if (normalSize == null || normalSize.getCurrencyPairGroup() == null) {
        throw new IllegalArgumentException("Currency pair is null.");
    }
    long custId = NumberUtils.toLong(normalSize.getCustId(), -1);
    if (custId <= -1) {
        throw new IllegalArgumentException("invalid cust id");
    }
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("saveCurrencyPairAndNormalSize").withSchemaName(SCHEMA_NAME);
    String[] params = new String[] { "I_PRICING_SERVICE_ID", "I_CUST_ID", "I_USER", "I_CCY1", "I_CCY2", "I_CCY1_SIZE", "I_CCY2_SIZE" };
    CurrencyPairGroup group = normalSize.getCurrencyPairGroup();
    try {
        int paramIndex = 0;
        int strType = OracleTypes.VARCHAR;
        int numType = OracleTypes.NUMBER;
        sp.addDeclareParameter(new SqlInParameter(params[paramIndex++], strType, group.getPricingServiceId()));
        sp.addDeclareParameter(new SqlInParameter(params[paramIndex++], numType, custId));
        sp.addDeclareParameter(new SqlInParameter(params[paramIndex++], strType, user));
        sp.addDeclareParameter(new SqlInParameter(params[paramIndex++], strType, group.getCcy1()));
        sp.addDeclareParameter(new SqlInParameter(params[paramIndex++], strType, group.getCcy2()));
        sp.addDeclareParameter(new SqlInParameter(params[paramIndex++], strType, normalSize.getCcy1NormalSize()));
        sp.addDeclareParameter(new SqlInParameter(params[paramIndex++], strType, normalSize.getCcy2NormalSize()));
        caller.execute(sp);
        LOGGER.info("saveCurrencyPairAndNormalSize ends...");
    } catch (SQLException e) {
        LOGGER.error("saveCurrencyPairAndNormalSize ends with exception...", e);
        throw new DatabaseAccessException("failed to save or update normal size", e);
    }
}


@Override
public List<JsonOrderNormalSize> updateJsonOrderNormalSize(List<JsonOrderNormalSize> normalSizes,String user){
    LOGGER.info("updateJsonOrderNormalSize list begins...");
    if (CollectionUtils.isEmpty(normalSizes)) {
        return null;
    }
    List<JsonOrderNormalSize> fails = new ArrayList<JsonOrderNormalSize>();
    for (JsonOrderNormalSize size : normalSizes) {
        try {
            saveCurrencyPairAndNormalSize(size, user);
        } catch (Exception e) {
            fails.add(size);
            LOGGER.error("updateJsonOrderNormalSize ends with exception...", e);
        }
    }
    LOGGER.info("updateJsonOrderNormalSize list ends...");
    return fails;
}


public void setDataSource(DataSource dataSource){
    this.caller = new JdbcCaller(dataSource);
}


@Override
public Map<String,BigDecimal> handle(ResultSet rs){
    Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
    while (rs.next()) {
        resultMap.put(rs.getString("PRICING_SERVICE_ID"), rs.getBigDecimal("VALUE"));
    }
    return resultMap;
}


@SuppressWarnings("unchecked")
@Override
public List<JsonOrderNormalSize> queryAllWithoutValue(){
    LOGGER.info("queryAllOrderNormalSize begins...");
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("queryAllOrderNormalSize").withSchemaName(SCHEMA_NAME);
    List<JsonOrderNormalSize> result = null;
    try {
        sp.addDeclareParameter(new SqlOutParameter("O_CURSOR", OracleTypes.CURSOR, new ResultSetHandler<List<JsonOrderNormalSize>>() {

            @Override
            public List<JsonOrderNormalSize> handle(ResultSet rs) throws SQLException {
                List<JsonOrderNormalSize> list = new ArrayList<JsonOrderNormalSize>();
                while (rs.next()) {
                    JsonOrderNormalSize normalSize = new JsonOrderNormalSize();
                    CurrencyPairGroup cpg = new CurrencyPairGroup();
                    String ccy1 = rs.getString("CCY1");
                    String ccy2 = rs.getString("CCY2");
                    cpg.setCcy1(ccy1);
                    cpg.setCcy2(ccy2);
                    cpg.setPricingServiceId(rs.getString("PRICING_SERVICE_ID"));
                    normalSize.setCurrencyPairGroup(cpg);
                    normalSize.setCustId(rs.getString("CUST_ID"));
                    list.add(normalSize);
                }
                return list;
            }
        }));
        final Map<String, Object> resultMap = caller.execute(sp);
        result = (List<JsonOrderNormalSize>) resultMap.get("O_CURSOR");
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        LOGGER.info("queryAllOrderNormalSize ends with exception...");
        throw new GenException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    LOGGER.info("queryAllOrderNormalSize ends...");
    return result;
}


@Override
public JsonOrderNormalSize queryOrderNormalSize(String groupId,String customerId){
    LOGGER.info("queryOrderNormalSize begins...");
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("queryOrderNormalSize").withSchemaName(SCHEMA_NAME);
    JsonOrderNormalSize jsonOrderNormalSize = null;
    try {
        sp.addDeclareParameter(new SqlInParameter("I_GROUP_ID", OracleTypes.VARCHAR, groupId));
        sp.addDeclareParameter(new SqlInParameter("I_CUSTOMER_ID", OracleTypes.VARCHAR, customerId));
        sp.addDeclareParameter(new SqlOutParameter("O_CURSOR", OracleTypes.CURSOR, new ResultSetHandler<JsonOrderNormalSize>() {

            @Override
            public JsonOrderNormalSize handle(ResultSet rs) throws SQLException {
                JsonOrderNormalSize orderNormalSize = new JsonOrderNormalSize();
                CurrencyPairGroup currencyPairGroup = new CurrencyPairGroup();
                while (rs.next()) {
                    currencyPairGroup.setGroupId(rs.getString("GROUP_ID"));
                    currencyPairGroup.setPricingServiceId(rs.getString("PRICING_SERVICE_ID"));
                    orderNormalSize.setCustId(rs.getString("CUST_ID"));
                    orderNormalSize.setCcy1NormalSize(rs.getBigDecimal("NORMAL_SIZE1"));
                    orderNormalSize.setCcy2NormalSize(rs.getBigDecimal("NORMAL_SIZE2"));
                    currencyPairGroup.setCcy1(rs.getString("CURRENCY1"));
                    currencyPairGroup.setCcy2(rs.getString("CURRENCY2"));
                    orderNormalSize.setCurrencyPairGroup(currencyPairGroup);
                }
                return orderNormalSize;
            }
        }));
        final Map<String, Object> resultMap = caller.execute(sp);
        jsonOrderNormalSize = (JsonOrderNormalSize) resultMap.get("O_CURSOR");
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        LOGGER.info("deleteOrderNormalSize ends with exception...");
        throw new GenException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    LOGGER.info("queryOrderNormalSize ends...");
    return jsonOrderNormalSize;
}


}