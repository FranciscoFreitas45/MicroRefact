package com.crontab;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import com.ssc.faw.util.GenException;
public interface OrderNormalSizeDao {


public Map<String,BigDecimal> queryDefaultValue(String validationId)
;

public void deleteOrderNormalSize(String productCategoryId,String customerId,String ccy1,String ccy2)
;

public List<JsonOrderNormalSize> updateJsonOrderNormalSize(List<JsonOrderNormalSize> normalSizes,String user)
;

public void saveCurrencyPairAndNormalSize(JsonOrderNormalSize normalSize,String user)
;

public void setDataSource(DataSource dataSource)
;

public List<JsonOrderNormalSize> queryAllWithoutValue()
;

public JsonOrderNormalSize queryOrderNormalSize(String groupId,String customerId)
;

}