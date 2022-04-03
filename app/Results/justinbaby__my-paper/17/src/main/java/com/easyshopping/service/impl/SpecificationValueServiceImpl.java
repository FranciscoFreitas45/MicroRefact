package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.SpecificationValueDao;
import com.easyshopping.entity.SpecificationValue;
import com.easyshopping.service.SpecificationValueService;
import org.springframework.stereotype.Service;
@Service("specificationValueServiceImpl")
public class SpecificationValueServiceImpl extends BaseServiceImpl<SpecificationValue, Long>implements SpecificationValueService{


@Resource(name = "specificationValueDaoImpl")
public void setBaseDao(SpecificationValueDao specificationValueDao){
    super.setBaseDao(specificationValueDao);
}


}