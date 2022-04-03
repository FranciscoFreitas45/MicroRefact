package com.crontab;
 import java.util.List;
public interface FxOrderService {


public void setFxOrderDao(FxOrderDao fxOrderDao)
;

public List<LegacyFxOrder> getFxOrderInRange(int dateRange,List<String> logNumKeyList)
;

}