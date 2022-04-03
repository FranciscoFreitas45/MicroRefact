package com.easyshopping.dao.impl;
 import com.easyshopping.dao.ShippingMethodDao;
import com.easyshopping.entity.ShippingMethod;
import org.springframework.stereotype.Repository;
@Repository("shippingMethodDaoImpl")
public class ShippingMethodDaoImpl extends BaseDaoImpl<ShippingMethod, Long>implements ShippingMethodDao{


}