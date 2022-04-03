package com.crontab;
 import java.util.List;
import javax.sql.DataSource;
public interface OrderValidationDao {


public String updateMinimalSize(JsonOrderSizeTier jsonOrderSizeTier,String user,String time)
;

public List<JsonCurrencyPairCutoff> updateDeadline(List<JsonCurrencyPairCutoff> list,String string,String time24)
;

public String deleteGlobalHoliday(String globalHolidayIds,String user,String currentDate)
;

public List<JsonOrderNormalSize> queryNormalSize(JsonOrderNormalSize jsonObj)
;

public void addTiers(JsonOrderSizeTier t,String user)
;

public String deleteTierAndMinimalSize(String generateGroupId,String user)
;

public JsonOrderNormalSize addNormalSizeByCustId(JsonOrderNormalSize jsonObj,String user)
;

public List<JsonCurrencyPairCutoff> queryDeadline(JsonCurrencyPairCutoff jsonObj)
;

public JsonOrderNormalSize addNormalSize(JsonOrderNormalSize jsonObj,String user)
;

public List<JsonOrderNormalSize> updateNormalSize(List<JsonOrderNormalSize> normalSizes,String user,String time)
;

public String deleteCurrencyPairCutoff(String groupIds,String user)
;

public List<JsonOrderSizeTier> queryTiers(JsonOrderSizeTier t)
;

public JsonGlobalHoliday addGlobalHoliday(JsonGlobalHoliday jsonObj,String user)
;

public JsonCurrencyPairCutoff addCurrencyPairCutoff(JsonCurrencyPairCutoff jsonObj,String user)
;

public List<JsonPricingService> queryPricingService()
;

public void setWSSDataSource(DataSource dataSource)
;

}