package com.crontab;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.test.CurrencyPairGroup;
public class QueryDeadlineResultSetHandler implements ResultSetHandler<List<JsonCurrencyPairCutoff>>{

 private  Logger LOGGER;


@Override
public List<JsonCurrencyPairCutoff> handle(ResultSet rs){
    LOGGER.info("query deadline result set handler start");
    List<JsonCurrencyPairCutoff> cutoffList = new ArrayList<JsonCurrencyPairCutoff>();
    while (rs.next()) {
        JsonCurrencyPairCutoff cutoff = new JsonCurrencyPairCutoff();
        CurrencyPairGroup group = new CurrencyPairGroup();
        group.setGroupId(rs.getString("GROUP_ID"));
        group.setCcy1(rs.getString("CCY1"));
        group.setCcy2(rs.getString("CCY2"));
        group.setPricingServiceId(rs.getString("PRICING_SERVICE_ID"));
        cutoff.setCurrencyPairGroup(group);
        cutoff.setCutoff(rs.getInt("CUTOFF"));
        cutoff.setLastUpdatedById(rs.getString("UPDATED_BY"));
        if (rs.getTimestamp("UPDATED_DATETIME") != null) {
            cutoff.setLastUpdatedDttm(rs.getTimestamp("UPDATED_DATETIME").getTime());
        }
        cutoffList.add(cutoff);
    }
    LOGGER.info("query deadline result set handler end");
    return cutoffList;
}


}