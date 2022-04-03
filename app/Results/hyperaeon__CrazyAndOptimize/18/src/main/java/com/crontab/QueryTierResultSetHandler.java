package com.crontab;
 import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.test.CurrencyPairGroup;
public class QueryTierResultSetHandler implements ResultSetHandler<List<JsonOrderSizeTier>>{

 private  Logger LOGGER;


@Override
public List<JsonOrderSizeTier> handle(ResultSet rs){
    LOGGER.info("QueryTierResultSetHandler handle start");
    List<JsonOrderSizeTier> jsonOrderSizeTierList = new LinkedList<JsonOrderSizeTier>();
    JsonOrderSizeTier jsonOrderSizeTier;
    CurrencyPairGroup currencyPairGroup;
    List<OrderSizeTier> ccy1Tiers;
    List<OrderSizeTier> ccy2Tiers;
    OrderSizeTier orderSizeTier;
    String ccy1;
    String ccy2;
    String currency;
    String groupId;
    while (rs.next()) {
        ccy1Tiers = new ArrayList<OrderSizeTier>();
        ccy2Tiers = new ArrayList<OrderSizeTier>();
        ccy1 = rs.getString("CCY1");
        ccy2 = rs.getString("CCY2");
        currency = rs.getString("CURRENCY") == null ? StringUtils.EMPTY : rs.getString("CURRENCY");
        groupId = rs.getString("GROUP_ID");
        currencyPairGroup = new CurrencyPairGroup();
        currencyPairGroup.setGroupId(groupId);
        currencyPairGroup.setPricingServiceId(rs.getString("PRICING_SERVICE_ID"));
        currencyPairGroup.setCcy1(ccy1);
        currencyPairGroup.setCcy2(ccy2);
        orderSizeTier = new OrderSizeTier();
        orderSizeTier.setFactor(rs.getDouble("FACTOR"));
        orderSizeTier.setMinSize(rs.getLong("MIN_SIZE"));
        orderSizeTier.setMaxSize(rs.getLong("MAX_SIZE"));
        orderSizeTier.setTierLevel(rs.getInt("TIER_LEVEL"));
        BigDecimal minimalSize = rs.getBigDecimal("MINIMAL_SIZE");
        if (ccy1 != null && ccy1.trim().equalsIgnoreCase(currency.trim())) {
            ccy1Tiers.add(orderSizeTier);
        } else if (ccy2 != null && ccy2.trim().equalsIgnoreCase(currency.trim())) {
            ccy2Tiers.add(orderSizeTier);
        }
        boolean isGroupIdExist = false;
        for (JsonOrderSizeTier tier : jsonOrderSizeTierList) {
            List<OrderSizeTier> tier1List = tier.getCcy1tiers();
            List<OrderSizeTier> tier2List = tier.getCcy2tiers();
            if (tier.getCurrencyPairGroup().getGroupId().trim().equalsIgnoreCase(groupId.trim())) {
                if (ccy1 != null && ccy1.trim().equalsIgnoreCase(currency.trim())) {
                    tier1List.addAll(ccy1Tiers);
                }
                if (ccy2 != null && ccy2.trim().equalsIgnoreCase(currency.trim())) {
                    tier2List.addAll(ccy2Tiers);
                }
                handleMinimalSize(currency, ccy1, ccy2, tier, minimalSize);
                isGroupIdExist = true;
            }
        }
        if (!isGroupIdExist) {
            jsonOrderSizeTier = new JsonOrderSizeTier();
            jsonOrderSizeTier.setCurrencyPairGroup(currencyPairGroup);
            jsonOrderSizeTier.setCcy1tiers(ccy1Tiers);
            jsonOrderSizeTier.setCcy2tiers(ccy2Tiers);
            jsonOrderSizeTierList.add(jsonOrderSizeTier);
            handleMinimalSize(currency, ccy1, ccy2, jsonOrderSizeTier, minimalSize);
        }
    }
    LOGGER.info("QueryTierResultSetHandler handle end");
    return jsonOrderSizeTierList;
}


public void handleMinimalSize(String currency,String ccy1,String ccy2,JsonOrderSizeTier jsonOrderSizeTier,BigDecimal minimalSize){
    if (ccy1 != null && ccy1.trim().equalsIgnoreCase(currency.trim()) && jsonOrderSizeTier.getCcy1MinimalSize() == null) {
        jsonOrderSizeTier.setCcy1MinimalSize(minimalSize);
    } else if (ccy2 != null && ccy2.trim().equalsIgnoreCase(currency.trim()) && jsonOrderSizeTier.getCcy2MinimalSize() == null) {
        jsonOrderSizeTier.setCcy2MinimalSize(minimalSize);
    }
}


}