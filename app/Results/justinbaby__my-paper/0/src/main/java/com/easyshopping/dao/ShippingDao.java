package com.easyshopping.dao;
 import com.easyshopping.entity.Shipping;
public interface ShippingDao extends BaseDao<Shipping, Long>{


public Shipping findBySn(String sn)
;

}