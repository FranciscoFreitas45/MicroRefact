package com.crontab;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class QueryPricingServiceResultSetHandler implements ResultSetHandler<List<PricingService>>{


@Override
public List<PricingService> handle(ResultSet rs){
    List<PricingService> list = new ArrayList<PricingService>();
    while (rs.next()) {
        PricingService ps = new PricingService();
        ps.setFullName(rs.getString("FULL_NAME"));
        ps.setOmsProdCateId(rs.getString("OMS_PROD_CATE_ID"));
        ps.setPricingServiceId(rs.getString("PRICING_SERVICE_ID"));
        ps.setShortName(rs.getString("SHORT_NAME"));
        ps.setUpdatedBy(rs.getString("UPDATED_BY"));
        ps.setUpdatedDatetime(rs.getDate("UPDATED_DATETIME"));
        list.add(ps);
    }
    return list;
}


}