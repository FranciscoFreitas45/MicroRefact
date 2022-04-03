package com.easyshopping.dao;
 import com.easyshopping.entity.DeliveryTemplate;
public interface DeliveryTemplateDao extends BaseDao<DeliveryTemplate, Long>{


public DeliveryTemplate findDefault()
;

}