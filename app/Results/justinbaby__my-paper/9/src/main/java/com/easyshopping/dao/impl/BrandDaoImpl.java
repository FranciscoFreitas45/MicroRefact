package com.easyshopping.dao.impl;
 import com.easyshopping.dao.BrandDao;
import com.easyshopping.entity.Brand;
import org.springframework.stereotype.Repository;
@Repository("brandDaoImpl")
public class BrandDaoImpl extends BaseDaoImpl<Brand, Long>implements BrandDao{


}