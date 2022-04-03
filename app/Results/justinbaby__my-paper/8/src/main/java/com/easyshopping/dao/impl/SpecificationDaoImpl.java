package com.easyshopping.dao.impl;
 import com.easyshopping.dao.SpecificationDao;
import com.easyshopping.entity.Specification;
import org.springframework.stereotype.Repository;
@Repository("specificationDaoImpl")
public class SpecificationDaoImpl extends BaseDaoImpl<Specification, Long>implements SpecificationDao{


}