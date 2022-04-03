package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.DeliveryCenterDao;
import com.easyshopping.entity.DeliveryCenter;
import com.easyshopping.service.DeliveryCenterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("deliveryCenterServiceImpl")
public class DeliveryCenterServiceImpl extends BaseServiceImpl<DeliveryCenter, Long>implements DeliveryCenterService{

@Resource(name = "deliveryCenterDaoImpl")
 private  DeliveryCenterDao deliveryCenterDao;


@Transactional(readOnly = true)
public DeliveryCenter findDefault(){
    return deliveryCenterDao.findDefault();
}


@Resource(name = "deliveryCenterDaoImpl")
public void setBaseDao(DeliveryCenterDao DeliveryCenterDao){
    super.setBaseDao(DeliveryCenterDao);
}


}