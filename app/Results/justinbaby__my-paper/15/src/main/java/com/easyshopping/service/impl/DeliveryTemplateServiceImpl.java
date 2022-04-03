package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.DeliveryTemplateDao;
import com.easyshopping.entity.DeliveryTemplate;
import com.easyshopping.service.DeliveryTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("deliveryTemplateServiceImpl")
public class DeliveryTemplateServiceImpl extends BaseServiceImpl<DeliveryTemplate, Long>implements DeliveryTemplateService{

@Resource(name = "deliveryTemplateDaoImpl")
 private  DeliveryTemplateDao deliveryTemplateDao;


@Transactional(readOnly = true)
public DeliveryTemplate findDefault(){
    return deliveryTemplateDao.findDefault();
}


@Resource(name = "deliveryTemplateDaoImpl")
public void setBaseDao(DeliveryTemplateDao deliveryTemplateDao){
    super.setBaseDao(deliveryTemplateDao);
}


}