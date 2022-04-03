package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.DeliveryCorpDao;
import com.easyshopping.entity.DeliveryCorp;
import com.easyshopping.service.DeliveryCorpService;
import org.springframework.stereotype.Service;
@Service("deliveryCorpServiceImpl")
public class DeliveryCorpServiceImpl extends BaseServiceImpl<DeliveryCorp, Long>implements DeliveryCorpService{


@Resource(name = "deliveryCorpDaoImpl")
public void setBaseDao(DeliveryCorpDao deliveryCorpDao){
    super.setBaseDao(deliveryCorpDao);
}


}