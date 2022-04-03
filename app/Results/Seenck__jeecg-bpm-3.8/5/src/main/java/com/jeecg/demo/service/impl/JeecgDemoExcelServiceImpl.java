package com.jeecg.demo.service.impl;
 import java.io.Serializable;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeecg.demo.entity.JeecgDemoExcelEntity;
import com.jeecg.demo.service.JeecgDemoExcelServiceI;
@Service("jeecgDemoExcelService")
@Transactional
public class JeecgDemoExcelServiceImpl extends CommonServiceImplimplements JeecgDemoExcelServiceI{

@Autowired
 private  NamedParameterJdbcTemplate namedParameterJdbcTemplate;


public Serializable save(JeecgDemoExcelEntity entity){
    Serializable t = super.save(entity);
    return t;
}


public void delete(JeecgDemoExcelEntity entity){
    super.delete(entity);
}


public void saveOrUpdate(JeecgDemoExcelEntity entity){
    super.saveOrUpdate(entity);
}


}