package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.ParameterDao;
import com.easyshopping.entity.Parameter;
import com.easyshopping.service.ParameterService;
import org.springframework.stereotype.Service;
@Service("parameterServiceImpl")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter, Long>implements ParameterService{


@Resource(name = "parameterDaoImpl")
public void setBaseDao(ParameterDao parameterDao){
    super.setBaseDao(parameterDao);
}


}