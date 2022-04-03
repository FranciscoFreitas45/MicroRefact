package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.ShippingMethodDao;
import com.easyshopping.entity.ShippingMethod;
import com.easyshopping.service.ShippingMethodService;
import org.springframework.stereotype.Service;
@Service("shippingMethodServiceImpl")
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethod, Long>implements ShippingMethodService{


@Resource(name = "shippingMethodDaoImpl")
public void setBaseDao(ShippingMethodDao shippingMethodDao){
    super.setBaseDao(shippingMethodDao);
}


}