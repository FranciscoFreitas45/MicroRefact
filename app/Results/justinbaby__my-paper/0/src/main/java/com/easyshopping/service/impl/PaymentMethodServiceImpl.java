package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.PaymentMethodDao;
import com.easyshopping.entity.PaymentMethod;
import com.easyshopping.service.PaymentMethodService;
import org.springframework.stereotype.Service;
@Service("paymentMethodServiceImpl")
public class PaymentMethodServiceImpl extends BaseServiceImpl<PaymentMethod, Long>implements PaymentMethodService{


@Resource(name = "paymentMethodDaoImpl")
public void setBaseDao(PaymentMethodDao paymentMethodDao){
    super.setBaseDao(paymentMethodDao);
}


}