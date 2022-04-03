package com.crontab;
 import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.test.CurrencyPairGroup;
public class QueryOrderNormalSizeResultSetHandler implements ResultSetHandler<List<JsonOrderNormalSize>>{

 private  Logger LOGGER;

 private  JsonOrderNormalSize jsonObj;

public QueryOrderNormalSizeResultSetHandler(JsonOrderNormalSize jsonObj) {
    this.jsonObj = jsonObj;
}
public void setNormalSize(String ccy1,String ccy2,String currency,BigDecimal normalSize,JsonOrderNormalSize jsonOrderNormalSize){
    if (ccy1 != null && ccy1.trim().equals(currency.trim()) && normalSize != null) {
        jsonOrderNormalSize.setCcy1NormalSize(normalSize);
    } else if (ccy2 != null && ccy2.trim().equals(currency.trim()) && normalSize != null) {
        jsonOrderNormalSize.setCcy2NormalSize(normalSize);
    }
}


@Override
public List<JsonOrderNormalSize> handle(ResultSet rs){
    LOGGER.info("Start to handle query order normal size!");
    Map<String, JsonOrderNormalSize> groupIdMap = new HashMap<String, JsonOrderNormalSize>();
    List<JsonOrderNormalSize> normalSizeList = new ArrayList<JsonOrderNormalSize>();
    while (rs.next()) {
        JsonOrderNormalSize jsonOrderNormalSize;
        String groupId = rs.getString("GROUP_ID");
        String pricingServiceId = rs.getString("PRICING_SERVICE_ID");
        String ccy1 = rs.getString("CCY1");
        String ccy2 = rs.getString("CCY2");
        String custId = rs.getString("CUST_ID");
        String currency = rs.getString("CURRENCY");
        BigDecimal normalSize = rs.getBigDecimal("NORMAL_SIZE");
        String lastUpdatedById = rs.getString("UPDATED_BY");
        Date lastUpdatedDttm = rs.getTimestamp("UPDATED_DATETIME");
        // ensure the value in map is unique.
        String groupIdCustId = groupId + Constants.UNDER_LINE + custId;
        if (groupIdMap.get(groupIdCustId) != null) {
            jsonOrderNormalSize = groupIdMap.get(groupIdCustId);
        } else {
            jsonOrderNormalSize = new JsonOrderNormalSize();
            CurrencyPairGroup group = new CurrencyPairGroup();
            group.setGroupId(groupId);
            group.setPricingServiceId(pricingServiceId);
            group.setCcy1(ccy1);
            group.setCcy2(ccy2);
            jsonOrderNormalSize.setCurrencyPairGroup(group);
            groupIdMap.put(groupIdCustId, jsonOrderNormalSize);
        }
        jsonOrderNormalSize.setIm(jsonObj.getIm());
        jsonOrderNormalSize.setFund(jsonObj.getFund());
        jsonOrderNormalSize.setCustId(custId);
        jsonOrderNormalSize.setLastUpdatedById(lastUpdatedById);
        jsonOrderNormalSize.setLastUpdatedDttm(lastUpdatedDttm.getTime());
        LOGGER.debug("jsonOrderNormalSize: " + jsonOrderNormalSize);
        setNormalSize(ccy1, ccy2, currency, normalSize, jsonOrderNormalSize);
    }
    Iterator<String> it = groupIdMap.keySet().iterator();
    while (it.hasNext()) {
        normalSizeList.add(groupIdMap.get(it.next()));
    }
    LOGGER.info("End to handle query order normal size!");
    return normalSizeList;
}


}