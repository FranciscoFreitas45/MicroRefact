package com.crontab;
 import java.util.List;
public interface OrderValidationService {


public OrderValidationContext updateMinimalSize(String json,String user)
;

public OrderValidationContext updateDeadline(String json,String user)
;

public OrderValidationContext deleteGlobalHoliday(String inputJson,String user)
;

public void setOrderValidationDao(OrderValidationDao dao)
;

public boolean queryNormalSize()
;

public String deleteDeadline()
;

public boolean addTiers()
;

public OrderValidationContext deleteTierAndMinimalSize(String inputJson,String user)
;

public OrderValidationContext addNormalSizeByCustId(String json,String user)
;

public boolean addDeadline()
;

public boolean queryDeadline()
;

public String addNormalSize()
;

public String updateNormalSize()
;

public boolean queryTiers()
;

public OrderValidationContext addGlobalHoliday(String inputJson,String user)
;

public OrderValidationContext queryPricingService()
;

public void setContext(OrderValidationContext context)
;

public OrderValidationContext getContext()
;

}