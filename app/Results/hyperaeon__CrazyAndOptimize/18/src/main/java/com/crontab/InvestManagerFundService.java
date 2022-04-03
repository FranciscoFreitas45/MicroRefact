package com.crontab;
 import java.util.List;
import com.test.InvestManagerFund;
public interface InvestManagerFundService {


public boolean isCustIdExist(String custId)
;

public void setContext(OrderValidationContext context)
;

public boolean queryCustIdByFund()
;

public String isFundExist(String fund)
;

public void setInvestManagerFundDao(InvestManagerFundDao dao)
;

public OrderValidationContext getContext()
;

public List<InvestManagerFund> fuzzySearchByImAndFund(String im,String fund)
;

public List<String> getCurrencyList()
;

}