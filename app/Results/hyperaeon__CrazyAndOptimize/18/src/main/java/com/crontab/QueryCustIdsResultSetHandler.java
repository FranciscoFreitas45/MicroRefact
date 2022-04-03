package com.crontab;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
public class QueryCustIdsResultSetHandler implements ResultSetHandler<List<JsonImFund>>{

 private  Logger LOGGER;


@Override
public List<JsonImFund> handle(ResultSet rs){
    LOGGER.debug("---QueryCustIdsResultSetHandler handle start---");
    List<JsonImFund> custIdFundList = new ArrayList<JsonImFund>();
    while (rs.next()) {
        JsonImFund imFund = new JsonImFund();
        imFund.setFund(rs.getString("CUST_SHRT_NM"));
        imFund.setCustId(rs.getBigDecimal("cust_id"));
        custIdFundList.add(imFund);
    }
    LOGGER.debug("---QueryCustIdsResultSetHandler handle end---");
    return custIdFundList;
}


}