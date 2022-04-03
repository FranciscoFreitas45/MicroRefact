package com.crontab;
 public interface OrderNormalSizeService {


public void calculateNormalSizeAsNeeded()
;

public void setNbaResourceConfigDao(NbaResourceConfigDao nbaResourceConfigDao)
;

public void deleteOrderNormalSize(String productCategoryId,String customerId,String ccy1,String ccy2)
;

public void setFxOrderService(FxOrderService fxOrderService)
;

public String calculateAllNormalSize(Calculator calculator,String user,String filterJson)
;

public void setOrderNormalSizeDao(OrderNormalSizeDao orderNormalSizeDao)
;

public void setPricingServiceDao(PricingServiceDao pricingServiceDao)
;

}