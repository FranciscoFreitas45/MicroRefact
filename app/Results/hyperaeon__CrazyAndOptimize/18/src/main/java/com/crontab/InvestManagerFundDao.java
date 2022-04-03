package com.crontab;
 import java.util.List;
import javax.sql.DataSource;
import com.test.InvestManagerFund;
public interface InvestManagerFundDao {


public void setLegacyOMSDataSource(DataSource dataSource)
;

public boolean isCustIdExist(String custId)
;

public List<JsonImFund> queryCustIdByFund(JsonImFund jsonObj)
;

public String isFundExist(String custName)
;

public List<InvestManagerFund> fuzzySearchByImAndFund(String im,String fund)
;

public List<String> getCurrencyList()
;

}