package com.easyshopping.dao.impl;
 import com.easyshopping.dao.AdPositionDao;
import com.easyshopping.entity.AdPosition;
import org.springframework.stereotype.Repository;
@Repository("adPositionDaoImpl")
public class AdPositionDaoImpl extends BaseDaoImpl<AdPosition, Long>implements AdPositionDao{


}