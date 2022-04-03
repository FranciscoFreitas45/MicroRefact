package com.crontab;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class QueryPricingServiceListResultSetHandler implements ResultSetHandler<List<JsonPricingService>>{


@Override
public List<JsonPricingService> handle(ResultSet rs){
    List<JsonPricingService> pricingServiceList = new ArrayList<JsonPricingService>();
    while (rs.next()) {
        JsonPricingService pricingService = new JsonPricingService();
        pricingService.setPricingServiceId(rs.getString("PRICING_SERVICE_ID"));
        pricingService.setShortName(rs.getString("SHORT_NAME"));
        pricingService.setFullName(rs.getString("FULL_NAME"));
        pricingServiceList.add(pricingService);
    }
    return pricingServiceList;
}


}