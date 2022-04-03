package com.easyshopping.service;
 import java.util.Map;
import com.easyshopping.entity.Shipping;
public interface ShippingService extends BaseService<Shipping, Long>{


public Shipping findBySn(String sn)
;

public Map<String,Object> query(Shipping shipping)
;

}