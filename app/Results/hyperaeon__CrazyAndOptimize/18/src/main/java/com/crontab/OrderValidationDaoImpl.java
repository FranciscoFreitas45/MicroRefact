package com.crontab;
 import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.test.CurrencyPairGroup;
public class OrderValidationDaoImpl implements OrderValidationDao{

 private  Logger LOGGER;

 private  String PACKAGE_NAME;

 private  String SCHEMA_NAME;

 private  String DB_CURSOR;

 private  JdbcCaller caller;

 private  String EXIST;


public boolean existTiers(String pricingServiceId,String ccy1,String ccy2){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("checkOrderSizeTier").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("iPRICING_SERVICE_ID", OracleTypes.VARCHAR, pricingServiceId.toUpperCase()));
        sp.addDeclareParameter(new SqlInParameter("iCCY1", OracleTypes.VARCHAR, ccy1.toUpperCase()));
        sp.addDeclareParameter(new SqlInParameter("iCCY2", OracleTypes.VARCHAR, ccy2.toUpperCase()));
        sp.addDeclareParameter(new SqlOutParameter("oCount", OracleTypes.NUMERIC));
        final Map<String, Object> resultMap = caller.execute(sp);
        return ((BigDecimal) resultMap.get("oCount")).intValue() > 0;
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


@Override
public String updateMinimalSize(JsonOrderSizeTier jsonOrderSizeTier,String user,String time){
    LOGGER.info("Begin in updateMinimalSize DAO");
    String currencyPairGroupParam = organizeCurrencyPairGroupParamForUpdate(jsonOrderSizeTier, user);
    String ccyTierParam1 = organizeCcy1TierParam(jsonOrderSizeTier);
    String ccyTierParam2 = organizeCcy2TierParam(jsonOrderSizeTier);
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("updateTiers").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("currencyPairGroup", OracleTypes.VARCHAR, currencyPairGroupParam));
        sp.addDeclareParameter(new SqlInParameter("ccy1Tiers", OracleTypes.VARCHAR, ccyTierParam1));
        sp.addDeclareParameter(new SqlInParameter("ccy2Tiers", OracleTypes.VARCHAR, ccyTierParam2));
        sp.addDeclareParameter(new SqlInParameter("l_current_time", OracleTypes.VARCHAR, time));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.VARCHAR));
        LOGGER.info("Begin in caller");
        final Map<String, Object> resultMap = caller.execute(sp);
        LOGGER.info("End in caller");
        String result = (String) resultMap.get("rs");
        if ("fail".equals(result)) {
            throw new OrderValidationException(Constants.ERROR_QUERY_NO_RESULT, Constants.RESP_LEVEL_INFO, null);
        }
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    LOGGER.info("End in updateMinimalSize DAO");
    return "success";
}


@Override
public String deleteGlobalHoliday(String globalHolidayIds,String user,String currentDate){
    LOGGER.info("Start to delete global holiday.");
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("deleteGlobalHoliday").withSchemaName(SCHEMA_NAME);
    StringBuilder builder = new StringBuilder();
    try {
        sp.addDeclareParameter(new SqlInParameter("l_global_holiday_id", OracleTypes.VARCHAR, globalHolidayIds));
        sp.addDeclareParameter(new SqlInParameter("l_current_date", OracleTypes.VARCHAR, currentDate));
        sp.addDeclareParameter(new SqlInParameter("l_user", OracleTypes.VARCHAR, user));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.VARCHAR));
        final Map<String, Object> resultMap = caller.execute(sp);
        String result = (String) resultMap.get("rs");
        if (result.indexOf(Constants.COMMA) != -1) {
            result = result.substring(0, result.length() - 1);
            builder.append("Global holiday id (").append(result);
            builder.append(") not found.");
            throw new OrderValidationException(Constants.ERROR_NO_NORMAL_SIZE, Constants.RESP_LEVEL_INFO, builder.toString(), null);
        } else {
            builder.append(result);
        }
    } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    LOGGER.info("End to delete global holiday.");
    return builder.toString();
}


public String isGroupIdExist(String groupIds){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("isGroupIdExist").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("iGroupId", OracleTypes.VARCHAR, groupIds));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.VARCHAR));
        final Map<String, Object> resultMap = caller.execute(sp);
        String result = (String) resultMap.get("rs");
        StringBuilder builder = new StringBuilder();
        if (result.indexOf(Constants.COMMA) != -1) {
            builder.append(result);
            builder.replace(result.length() - 1, result.length(), StringUtils.EMPTY);
        } else {
            builder.append(result);
        }
        return builder.toString();
    } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


@Override
public void addTiers(JsonOrderSizeTier t,String user){
    LOGGER.info("Start in addTiers DAO");
    // add the tiers and return the new group id. make sure the user name is also updated. If ccyGroup doesn't exist, add first before adding tiers.
    if (existTiers(t.getCurrencyPairGroup().getPricingServiceId().trim(), t.getCurrencyPairGroup().getCcy1().trim(), t.getCurrencyPairGroup().getCcy2().trim()) || !addOrderSizeTiers(organizeCurrencyPairGroupParam(t, user), organizeCcy1TierParam(t), organizeCcy2TierParam(t), t.getCurrencyPairGroup())) {
        throw new OrderValidationException(Constants.ERROR_DUPLICATION, Constants.RESP_LEVEL_INFO, null);
    }
    LOGGER.info("End in addTiers DAO");
}


public String organizeParamForUpdateNormalSize(JsonOrderNormalSize t,String user){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(t.getCurrencyPairGroup().getCcy1().toUpperCase().trim()).append(Constants.COMMA).append(t.getCurrencyPairGroup().getCcy2().toUpperCase().trim()).append(Constants.COMMA).append(t.getCurrencyPairGroup().getGroupId().toUpperCase().trim()).append(Constants.COMMA).append(t.getCustId()).append(Constants.COMMA).append(t.getCcy1NormalSize() == null ? StringUtils.EMPTY : t.getCcy1NormalSize()).append(Constants.COMMA).append(t.getCcy2NormalSize() == null ? StringUtils.EMPTY : t.getCcy2NormalSize()).append(Constants.COMMA).append(user);
    return stringBuilder.toString();
}


@Override
public JsonOrderNormalSize addNormalSize(JsonOrderNormalSize jsonObj,String user){
    LOGGER.info("Begin in addNormalSize DAO");
    JsonOrderNormalSize orderNormalSize = addNormalSize(organizeParamForNormalSize(jsonObj, user), jsonObj.getCurrencyPairGroup());
    if (orderNormalSize == null) {
        throw new OrderValidationException(Constants.ERROR_DUPLICATION, Constants.RESP_LEVEL_INFO, null);
    }
    LOGGER.info("End in addNormalSize DAO");
    return orderNormalSize;
}


public void handleEmptyTiers(List<JsonOrderSizeTier> tierList){
    Iterator<JsonOrderSizeTier> iterator = tierList.iterator();
    while (iterator.hasNext()) {
        JsonOrderSizeTier jsonOrderSizeTier = iterator.next();
        if (recordEmpty(jsonOrderSizeTier)) {
            iterator.remove();
        }
    }
}


public String organizeCcy1TierParam(JsonOrderSizeTier t){
    List<OrderSizeTier> ccy1Tiers = t.getCcy1tiers();
    if (CollectionUtils.isEmpty(ccy1Tiers)) {
        return StringUtils.EMPTY;
    }
    return organizeCcyTierParam(ccy1Tiers);
}


public List<JsonOrderNormalSize> updateNormalSize(List<JsonOrderNormalSize> normalSizes,String user,String time){
    LOGGER.info("start to update normal order size!");
    StringBuilder builder = new StringBuilder("Order normal size of ");
    boolean flag = true;
    for (JsonOrderNormalSize o : normalSizes) {
        try {
            Integer custId = Integer.valueOf(o.getCustId().trim());
            String s = isNormalSizeExist(o.getCurrencyPairGroup().getGroupId().toUpperCase().trim(), custId);
            if (!EXIST.equals(s)) {
                flag = false;
                builder.append("(").append(s).append("), ");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new OrderValidationException(Constants.ERROR_VALUE_INVALID, Constants.RESP_LEVEL_INFO, null);
        }
    }
    builder.replace(builder.length() - 1, builder.length(), "");
    builder.append(" not found!");
    if (!flag) {
        throw new OrderValidationException(Constants.ERROR_NO_NORMAL_SIZE, Constants.RESP_LEVEL_INFO, builder.toString(), null);
    } else {
        List<AbstractCallableStatement> sps = generateListFromJsonObj(normalSizes, user, time);
        try {
            caller.transactionExcute(sps);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
        }
    }
    LOGGER.info("the end to update normal order size!");
    return normalSizes;
}


public JsonCurrencyPairCutoff addCurrencyPairCutoff(JsonCurrencyPairCutoff jsonObj,String user){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("addCurrencyPairCutoff").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("iPRICING_SERVICE_ID", OracleTypes.VARCHAR, jsonObj.getCurrencyPairGroup().getPricingServiceId().toUpperCase().trim()));
        sp.addDeclareParameter(new SqlInParameter("iCCY1", OracleTypes.VARCHAR, jsonObj.getCurrencyPairGroup().getCcy1().toUpperCase().trim()));
        sp.addDeclareParameter(new SqlInParameter("iCCY2", OracleTypes.VARCHAR, jsonObj.getCurrencyPairGroup().getCcy2().toUpperCase().trim()));
        sp.addDeclareParameter(new SqlInParameter("iCUTOFF", OracleTypes.VARCHAR, jsonObj.getCutoff().toString()));
        sp.addDeclareParameter(new SqlInParameter("iUPDATED_BY", OracleTypes.VARCHAR, user));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.VARCHAR));
        sp.addDeclareParameter(new SqlOutParameter("l_groupId", Types.VARCHAR));
        final Map<String, Object> resultMap = caller.execute(sp);
        String result = (String) resultMap.get("rs");
        if (Integer.valueOf(result) != 0) {
            throw new OrderValidationException(Constants.ERROR_DUPLICATION, Constants.RESP_LEVEL_INFO, null);
        }
        jsonObj.getCurrencyPairGroup().setGroupId((String) resultMap.get("l_groupId"));
        jsonObj.getCurrencyPairGroup().setPricingServiceId(jsonObj.getCurrencyPairGroup().getPricingServiceId().toUpperCase().trim());
        jsonObj.getCurrencyPairGroup().setCcy1(jsonObj.getCurrencyPairGroup().getCcy1().toUpperCase().trim());
        jsonObj.getCurrencyPairGroup().setCcy2(jsonObj.getCurrencyPairGroup().getCcy2().toUpperCase().trim());
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    return jsonObj;
}


@Override
public List<JsonPricingService> queryPricingService(){
    LOGGER.info("Start to query pricing servie.");
    try {
        final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("queryPricingService").withSchemaName(SCHEMA_NAME);
        sp.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryPricingServiceListResultSetHandler()));
        final Map<String, Object> resultMap = caller.execute(sp);
        List<JsonPricingService> pricingServiceList = (List<JsonPricingService>) resultMap.get(DB_CURSOR);
        // If there is no result with query
        if (CollectionUtils.isEmpty(pricingServiceList)) {
            throw new OrderValidationException(Constants.ERROR_QUERY_NO_RESULT, Constants.RESP_LEVEL_INFO, null);
        }
        return pricingServiceList;
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


@Override
public void setWSSDataSource(DataSource dataSource){
    this.caller = new JdbcCaller(dataSource);
}


public List<JsonCurrencyPairCutoff> updateDeadline(List<JsonCurrencyPairCutoff> list,String string,String time24){
    LOGGER.info("Start to update dead line!");
    StringBuilder builder = new StringBuilder("Deadline of ");
    boolean flag = true;
    StringBuilder groupIdBuilder = new StringBuilder();
    for (JsonCurrencyPairCutoff cutoff : list) {
        groupIdBuilder.append(cutoff.getCurrencyPairGroup().getGroupId()).append(Constants.SEMICOLON);
    }
    groupIdBuilder.replace(groupIdBuilder.length() - 1, groupIdBuilder.length(), StringUtils.EMPTY);
    String s = isGroupIdExist(groupIdBuilder.toString());
    if (!EXIST.equals(s)) {
        flag = false;
        builder.append(s).append(" not found!");
    }
    if (!flag) {
        throw new OrderValidationException(Constants.ERROR_NO_NORMAL_SIZE, Constants.RESP_LEVEL_INFO, builder.toString(), null);
    } else {
        List<AbstractCallableStatement> sps = generateStatementListFromCutoffJsonObj(list, string, time24);
        try {
            caller.transactionExcute(sps);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
        }
    }
    LOGGER.info("End to update dead line!");
    return list;
}


public String organizeCcy2TierParam(JsonOrderSizeTier t){
    List<OrderSizeTier> ccy2Tiers = t.getCcy2tiers();
    if (CollectionUtils.isEmpty(ccy2Tiers)) {
        return StringUtils.EMPTY;
    }
    return organizeCcyTierParam(ccy2Tiers);
}


public List<JsonOrderNormalSize> queryNormalSize(JsonOrderNormalSize jsonObj){
    // init params for query
    String pricingServiceId = StringUtils.EMPTY;
    String ccy1 = StringUtils.EMPTY;
    String ccy2 = StringUtils.EMPTY;
    if (jsonObj.getCurrencyPairGroup() != null) {
        pricingServiceId = StringUtils.isBlank(jsonObj.getCurrencyPairGroup().getPricingServiceId()) ? StringUtils.EMPTY : jsonObj.getCurrencyPairGroup().getPricingServiceId().toUpperCase().trim();
        ccy1 = StringUtils.isBlank(jsonObj.getCurrencyPairGroup().getCcy1()) ? StringUtils.EMPTY : jsonObj.getCurrencyPairGroup().getCcy1().toUpperCase().trim();
        ccy2 = StringUtils.isBlank(jsonObj.getCurrencyPairGroup().getCcy2()) ? StringUtils.EMPTY : jsonObj.getCurrencyPairGroup().getCcy2().toUpperCase().trim();
    }
    try {
        List<JsonOrderNormalSize> allNormalSizeList = new ArrayList<JsonOrderNormalSize>();
        int size = jsonObj.getCustIdArr() == null ? 0 : jsonObj.getCustIdArr().length;
        for (int i = 0; i < size; i++) {
            final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("queryOrderNormalSize").withSchemaName(SCHEMA_NAME);
            jsonObj.setCustId(jsonObj.getCustIdArr()[i]);
            sp.addDeclareParameter(new SqlInParameter("iPRICING_SERVICE_ID", OracleTypes.VARCHAR, pricingServiceId));
            sp.addDeclareParameter(new SqlInParameter("iCCY1", OracleTypes.VARCHAR, ccy1));
            sp.addDeclareParameter(new SqlInParameter("iCCY2", OracleTypes.VARCHAR, ccy2));
            sp.addDeclareParameter(new SqlInParameter("iCUST_ID", OracleTypes.VARCHAR, jsonObj.getCustId()));
            sp.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryOrderNormalSizeResultSetHandler(jsonObj)));
            final Map<String, Object> resultMap = caller.execute(sp);
            List<JsonOrderNormalSize> normalSizeList = (List<JsonOrderNormalSize>) resultMap.get(DB_CURSOR);
            if (CollectionUtils.isNotEmpty(normalSizeList)) {
                allNormalSizeList.addAll(normalSizeList);
            }
        }
        // If there is no result with query
        if (CollectionUtils.isEmpty(allNormalSizeList)) {
            throw new OrderValidationException(Constants.ERROR_QUERY_NO_RESULT, Constants.RESP_LEVEL_INFO, null);
        }
        Collections.sort(allNormalSizeList);
        return allNormalSizeList;
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


public String organizeCcyTierParam(List<OrderSizeTier> ccyTiers){
    StringBuilder stringBuilder = new StringBuilder();
    for (OrderSizeTier tier : ccyTiers) {
        stringBuilder.append(tier.getFactor()).append(Constants.COMMA).append(tier.getMinSize()).append(Constants.COMMA).append(tier.getMaxSize()).append(Constants.COMMA).append(tier.getTierLevel()).append(Constants.SEMICOLON);
    }
    stringBuilder.replace(stringBuilder.lastIndexOf(Constants.SEMICOLON), stringBuilder.length(), StringUtils.EMPTY);
    return stringBuilder.toString();
}


@Override
public String deleteTierAndMinimalSize(String generateGroupId,String user){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("deleteTierAndMinimalSize").withSchemaName(SCHEMA_NAME);
    StringBuilder builder = new StringBuilder();
    try {
        sp.addDeclareParameter(new SqlInParameter("l_group_id", OracleTypes.VARCHAR, generateGroupId));
        sp.addDeclareParameter(new SqlInParameter("l_user", OracleTypes.VARCHAR, user));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.VARCHAR));
        final Map<String, Object> resultMap = caller.execute(sp);
        String result = (String) resultMap.get("rs");
        if (result.indexOf(Constants.COMMA) != -1) {
            result = result.substring(0, result.length() - 1);
            builder.append("Tier/Minimal size of group id (").append(result);
            builder.append(") not found.");
            throw new OrderValidationException(Constants.ERROR_NO_NORMAL_SIZE, Constants.RESP_LEVEL_INFO, builder.toString(), null);
        } else {
            builder.append(result);
        }
    } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    return builder.toString();
}


public JsonOrderNormalSize addNormalSizeByCustId(String currencyPairGroupParam,CurrencyPairGroup t){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("addNormalSizeByCustId").withSchemaName(SCHEMA_NAME);
    try {
        Date currentDate = new Date();
        DateFormat format24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time24 = format24.format(currentDate);
        sp.addDeclareParameter(new SqlInParameter("inputStr", OracleTypes.VARCHAR, currencyPairGroupParam));
        sp.addDeclareParameter(new SqlInParameter("iCURRENT_DATE", OracleTypes.VARCHAR, time24));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.INTEGER));
        sp.addDeclareParameter(new SqlOutParameter("rgroupId", Types.VARCHAR));
        final Map<String, Object> resultMap = caller.execute(sp);
        Integer result = (Integer) resultMap.get("rs");
        if (result != 0) {
            return null;
        }
        t.setGroupId((String) resultMap.get("rgroupId"));
        t.setCcy1(t.getCcy1().toUpperCase().trim());
        t.setCcy2(t.getCcy2().toUpperCase().trim());
        t.setPricingServiceId(t.getPricingServiceId().toUpperCase().trim());
        JsonOrderNormalSize normalOrderSize = new JsonOrderNormalSize();
        normalOrderSize.setCurrencyPairGroup(t);
        return normalOrderSize;
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


public boolean recordEmpty(JsonOrderSizeTier jsonOrderSizeTier){
    return CollectionUtils.isEmpty(jsonOrderSizeTier.getCcy1tiers()) || CollectionUtils.isEmpty(jsonOrderSizeTier.getCcy2tiers()) || jsonOrderSizeTier.getCcy1MinimalSize() == null || jsonOrderSizeTier.getCcy2MinimalSize() == null;
}


public String organizeCurrencyPairGroupParamForUpdate(JsonOrderSizeTier t,String user){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(organizeCurrencyPairGroupUserForUpdate(t, user)).append(t.getCcy1MinimalSize() == null ? StringUtils.EMPTY : t.getCcy1MinimalSize()).append(Constants.COMMA).append(t.getCcy2MinimalSize() == null ? StringUtils.EMPTY : t.getCcy2MinimalSize());
    return stringBuilder.toString();
}


public String organizeCurrencyPairGroupParam(JsonOrderSizeTier t,String user){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(organizeCurrencyPairGroupUser(t, user)).append(t.getCcy1MinimalSize() == null ? StringUtils.EMPTY : t.getCcy1MinimalSize()).append(Constants.COMMA).append(t.getCcy2MinimalSize() == null ? StringUtils.EMPTY : t.getCcy2MinimalSize());
    return stringBuilder.toString();
}


public List<JsonCurrencyPairCutoff> queryDeadline(JsonCurrencyPairCutoff jsonObj){
    String pricingServiceId = StringUtils.isBlank(jsonObj.getCurrencyPairGroup().getPricingServiceId()) ? StringUtils.EMPTY : jsonObj.getCurrencyPairGroup().getPricingServiceId().toUpperCase().trim();
    String ccy1 = StringUtils.isBlank(jsonObj.getCurrencyPairGroup().getCcy1()) ? StringUtils.EMPTY : jsonObj.getCurrencyPairGroup().getCcy1().toUpperCase().trim();
    String ccy2 = StringUtils.isBlank(jsonObj.getCurrencyPairGroup().getCcy2()) ? StringUtils.EMPTY : jsonObj.getCurrencyPairGroup().getCcy2().toUpperCase().trim();
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("queryDeadline").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("iPRICING_SERVICE_ID", OracleTypes.VARCHAR, pricingServiceId));
        sp.addDeclareParameter(new SqlInParameter("iCCY1", OracleTypes.VARCHAR, ccy1));
        sp.addDeclareParameter(new SqlInParameter("iCCY2", OracleTypes.VARCHAR, ccy2));
        sp.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryDeadlineResultSetHandler()));
        final Map<String, Object> resultMap = caller.execute(sp);
        List<JsonCurrencyPairCutoff> cutoffList = (List<JsonCurrencyPairCutoff>) resultMap.get(DB_CURSOR);
        if (CollectionUtils.isEmpty(cutoffList)) {
            throw new OrderValidationException(Constants.ERROR_QUERY_NO_RESULT, Constants.RESP_LEVEL_INFO, null);
        }
        return cutoffList;
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


public String organizeParamForUpdateDeadline(JsonCurrencyPairCutoff jsonCurrencyPairCutoff,String user){
    StringBuilder builder = new StringBuilder();
    builder.append(jsonCurrencyPairCutoff.getCurrencyPairGroup().getGroupId()).append(Constants.COMMA).append(jsonCurrencyPairCutoff.getCutoff()).append(Constants.COMMA).append(user);
    return builder.toString();
}


@Override
public String deleteCurrencyPairCutoff(String groupIds,String user){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("deleteCurrencyPairCutoff").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("l_group_id", OracleTypes.VARCHAR, groupIds));
        sp.addDeclareParameter(new SqlInParameter("l_user", OracleTypes.VARCHAR, user));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.VARCHAR));
        final Map<String, Object> resultMap = caller.execute(sp);
        String result = (String) resultMap.get("rs");
        if (result != null) {
            StringBuilder builder = new StringBuilder("Currency Piar Cutoff of(");
            result = result.substring(0, result.length() - 1);
            builder.append(result);
            builder.append("), Not Found!");
            throw new OrderValidationException(Constants.ERROR_NO_NORMAL_SIZE, Constants.RESP_LEVEL_INFO, builder.toString(), null);
        }
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_INFO, e);
    }
    return "success";
}


@SuppressWarnings("unchecked")
@Override
public List<JsonOrderSizeTier> queryTiers(JsonOrderSizeTier t){
    LOGGER.info("in queryTiers DAO");
    // init params. set the param with input JsonOrderSizeTier.when any of ccy1, ccy2 and pricingServiceID is null, it means there is no filter creteria
    String pricingServiceId;
    String ccy1;
    String ccy2;
    if (t.getCurrencyPairGroup() == null) {
        pricingServiceId = ccy1 = ccy2 = StringUtils.EMPTY;
    } else {
        pricingServiceId = StringUtils.isBlank(t.getCurrencyPairGroup().getPricingServiceId()) ? StringUtils.EMPTY : t.getCurrencyPairGroup().getPricingServiceId();
        ccy1 = StringUtils.isBlank(t.getCurrencyPairGroup().getCcy1()) ? StringUtils.EMPTY : t.getCurrencyPairGroup().getCcy1();
        ccy2 = StringUtils.isBlank(t.getCurrencyPairGroup().getCcy2()) ? StringUtils.EMPTY : t.getCurrencyPairGroup().getCcy2();
    }
    // Step 2: Query out and set back with list of object JsonOrderSizeTier
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("queryOderSizeTier").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("iPRICING_SERVICE_ID", OracleTypes.VARCHAR, pricingServiceId));
        sp.addDeclareParameter(new SqlInParameter("iCCY1", OracleTypes.VARCHAR, ccy1));
        sp.addDeclareParameter(new SqlInParameter("iCCY2", OracleTypes.VARCHAR, ccy2));
        sp.addDeclareParameter(new SqlOutParameter(DB_CURSOR, OracleTypes.CURSOR, new QueryTierResultSetHandler()));
        final Map<String, Object> resultMap = caller.execute(sp);
        List<JsonOrderSizeTier> tierList = (List<JsonOrderSizeTier>) resultMap.get(DB_CURSOR);
        handleEmptyTiers(tierList);
        // If there is no result with query
        if (CollectionUtils.isEmpty(tierList)) {
            throw new OrderValidationException(Constants.ERROR_QUERY_NO_RESULT, Constants.RESP_LEVEL_INFO, null);
        }
        return tierList;
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


@Override
public JsonGlobalHoliday addGlobalHoliday(JsonGlobalHoliday jsonObj,String user){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("addGlobalHoliday").withSchemaName(SCHEMA_NAME);
    String comments = StringUtils.EMPTY;
    if (!StringUtils.isBlank(jsonObj.getComments())) {
        comments = jsonObj.getComments().trim();
    }
    try {
        sp.addDeclareParameter(new SqlInParameter("iGlobalHolidayName", OracleTypes.VARCHAR, jsonObj.getGlobalHolidayName().trim()));
        sp.addDeclareParameter(new SqlInParameter("iGlobalHolidayDate", OracleTypes.VARCHAR, jsonObj.getGlobalHolidayDate().trim()));
        sp.addDeclareParameter(new SqlInParameter("iComments", OracleTypes.VARCHAR, comments));
        sp.addDeclareParameter(new SqlInParameter("iCurrentDate", OracleTypes.VARCHAR, getCurrentDate()));
        sp.addDeclareParameter(new SqlInParameter("iUser", OracleTypes.VARCHAR, user));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.VARCHAR));
        sp.addDeclareParameter(new SqlOutParameter("globalHolidayId", Types.INTEGER));
        final Map<String, Object> resultMap = caller.execute(sp);
        String result = (String) resultMap.get("rs");
        if (!"success".equals(result)) {
            throw new OrderValidationException(Constants.ERROR_DUPLICATION, Constants.RESP_LEVEL_INFO, null);
        }
        jsonObj.setGlobalHolidayId((Integer) resultMap.get("globalHolidayId"));
        jsonObj.setComments(StringUtils.isBlank(jsonObj.getComments()) ? StringUtils.EMPTY : jsonObj.getComments().trim());
        jsonObj.setGlobalHolidayDate(jsonObj.getGlobalHolidayDate().trim());
        jsonObj.setGlobalHolidayName(jsonObj.getGlobalHolidayName().trim());
    } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    return jsonObj;
}


public String getCurrentDate(){
    Date currentDate = new Date();
    DateFormat format24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return format24.format(currentDate);
}


public StringBuilder organizeCurrencyPairGroupUserForUpdate(JsonOrderSizeTier t,String user){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(t.getCurrencyPairGroup().getCcy1().trim().toUpperCase()).append(Constants.COMMA).append(t.getCurrencyPairGroup().getCcy2().trim().toUpperCase()).append(Constants.COMMA).append(t.getCurrencyPairGroup().getGroupId().trim().toUpperCase()).append(Constants.COMMA).append(user).append(Constants.COMMA);
    return stringBuilder;
}


public boolean addOrderSizeTiers(String currencyPairGroupParam,String ccyTierParam1,String ccyTierParam2,CurrencyPairGroup t){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("addTiers").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("currencyPairGroup", OracleTypes.VARCHAR, currencyPairGroupParam));
        sp.addDeclareParameter(new SqlInParameter("ccy1Tiers", OracleTypes.VARCHAR, ccyTierParam1));
        sp.addDeclareParameter(new SqlInParameter("ccy2Tiers", OracleTypes.VARCHAR, ccyTierParam2));
        sp.addDeclareParameter(new SqlInParameter("l_current_date", OracleTypes.VARCHAR, getCurrentDate()));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.INTEGER));
        sp.addDeclareParameter(new SqlOutParameter("l_groupId", Types.VARCHAR));
        final Map<String, Object> resultMap = caller.execute(sp);
        Integer result = (Integer) resultMap.get("rs");
        // result 1/2 means tiers for ccy 1/2 has duplicates.
        if (result != 0) {
            return false;
        }
        t.setGroupId((String) resultMap.get("l_groupId"));
        t.setCcy1(t.getCcy1().toUpperCase());
        t.setCcy2(t.getCcy2().toUpperCase());
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    return true;
}


public StringBuilder organizeCurrencyPairGroupUser(JsonOrderSizeTier t,String user){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(t.getCurrencyPairGroup().getCcy1().toUpperCase()).append(Constants.COMMA).append(t.getCurrencyPairGroup().getCcy2().toUpperCase()).append(Constants.COMMA).append(t.getCurrencyPairGroup().getPricingServiceId().toUpperCase()).append(Constants.COMMA).append(user).append(Constants.COMMA);
    return stringBuilder;
}


public String organizeParamForNormalSize(JsonOrderNormalSize t,String user){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(t.getCurrencyPairGroup().getCcy1().toUpperCase().trim()).append(Constants.COMMA).append(t.getCurrencyPairGroup().getCcy2().toUpperCase().trim()).append(Constants.COMMA).append(t.getCurrencyPairGroup().getPricingServiceId().toUpperCase().trim()).append(Constants.COMMA).append(t.getCustId().trim()).append(Constants.COMMA).append(t.getCcy1NormalSize() == null ? StringUtils.EMPTY : t.getCcy1NormalSize()).append(Constants.COMMA).append(t.getCcy2NormalSize() == null ? StringUtils.EMPTY : t.getCcy2NormalSize()).append(Constants.COMMA).append(user);
    return stringBuilder.toString();
}


public List<AbstractCallableStatement> generateListFromJsonObj(List<JsonOrderNormalSize> jsonOrderNormalSizes,String user,String time){
    List<AbstractCallableStatement> abstractCallableStatements = new ArrayList<AbstractCallableStatement>();
    Iterator<JsonOrderNormalSize> iterator = jsonOrderNormalSizes.iterator();
    while (iterator.hasNext()) {
        String input = organizeParamForUpdateNormalSize(iterator.next(), user);
        final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("updateNormalSize").withSchemaName(SCHEMA_NAME);
        try {
            sp.addDeclareParameter(new SqlInParameter("inputStr", OracleTypes.VARCHAR, input));
            sp.addDeclareParameter(new SqlInParameter("l_current_date", OracleTypes.VARCHAR, time));
            sp.addDeclareParameter(new SqlOutParameter("rs", Types.INTEGER));
            abstractCallableStatements.add(sp);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
        }
    }
    return abstractCallableStatements;
}


public List<AbstractCallableStatement> generateStatementListFromCutoffJsonObj(List<JsonCurrencyPairCutoff> cutoffList,String user,String time){
    List<AbstractCallableStatement> statements = new ArrayList<AbstractCallableStatement>();
    int size = cutoffList.size();
    try {
        for (int i = 0; i < size; i++) {
            String input = organizeParamForUpdateDeadline(cutoffList.get(i), user);
            final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("updateDeadline").withSchemaName(SCHEMA_NAME);
            sp.addDeclareParameter(new SqlInParameter("inputStr", OracleTypes.VARCHAR, input));
            sp.addDeclareParameter(new SqlInParameter("l_current_date", OracleTypes.VARCHAR, time));
            sp.addDeclareParameter(new SqlOutParameter("rs", Types.INTEGER));
            statements.add(sp);
        }
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
    return statements;
}


public String isNormalSizeExist(String groupId,Integer custId){
    final AbstractCallableStatement sp = new StoredProcedure().withCatalogName(PACKAGE_NAME).withProcedureName("isNormalSizeExist").withSchemaName(SCHEMA_NAME);
    try {
        sp.addDeclareParameter(new SqlInParameter("l_group_id", OracleTypes.VARCHAR, groupId));
        sp.addDeclareParameter(new SqlInParameter("l_cust_id", OracleTypes.NUMBER, custId));
        sp.addDeclareParameter(new SqlOutParameter("rs", Types.VARCHAR));
        final Map<String, Object> resultMap = caller.execute(sp);
        return (String) resultMap.get("rs");
    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
        throw new OrderValidationException(Constants.ERROR_SERVER, Constants.RESP_LEVEL_ERROR, e);
    }
}


}