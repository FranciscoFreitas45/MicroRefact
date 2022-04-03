package com.easyshopping.dao.impl;
 import com.easyshopping.dao.PaymentMethodDao;
import com.easyshopping.entity.PaymentMethod;
import org.springframework.stereotype.Repository;
@Repository("paymentMethodDaoImpl")
public class PaymentMethodDaoImpl extends BaseDaoImpl<PaymentMethod, Long>implements PaymentMethodDao{


}