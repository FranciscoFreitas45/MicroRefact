package com.easyshopping.dao.impl;
 import com.easyshopping.dao.AdDao;
import com.easyshopping.entity.Ad;
import org.springframework.stereotype.Repository;
@Repository("adDaoImpl")
public class AdDaoImpl extends BaseDaoImpl<Ad, Long>implements AdDao{


}