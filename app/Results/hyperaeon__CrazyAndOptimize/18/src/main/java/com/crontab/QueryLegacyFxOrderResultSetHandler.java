package com.crontab;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class QueryLegacyFxOrderResultSetHandler implements ResultSetHandler<List<LegacyFxOrder>>{


@Override
public List<LegacyFxOrder> handle(ResultSet rs){
    List<LegacyFxOrder> list = new ArrayList<LegacyFxOrder>();
    while (rs.next()) {
        LegacyFxOrder order = new LegacyFxOrder();
        order.setOrdrId(rs.getString("ordr_id"));
        order.setProductCategoryId(rs.getString("product_category_id"));
        order.setBaseCrncyId(rs.getString("base_crncy_id"));
        order.setBaseCrncyAmt(rs.getBigDecimal("base_crncy_amt"));
        order.setCntrCrncyId(rs.getString("cntr_crncy_id"));
        order.setCntrCrncyAmt(rs.getBigDecimal("cntr_crncy_amt"));
        order.setCustId(rs.getString("cust_id"));
        order.setIsAggregate(rs.getString("is_aggregate"));
        order.setIsBackToBack(rs.getString("is_back_to_back"));
        order.setOrdrTypeId(rs.getString("ordr_type_id"));
        order.setLogNum(rs.getString("log_num"));
        list.add(order);
    }
    return list;
}


}