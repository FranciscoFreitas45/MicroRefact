package com.oa.service.impl;
 import com.oa.service.OaHallServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.oa.entity.OaHallEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;
@Service("oaHallService")
@Transactional
public class OaHallServiceImpl extends CommonServiceImplimplements OaHallServiceI{

@Autowired
 private  NamedParameterJdbcTemplate namedParameterJdbcTemplate;


public Serializable save(OaHallEntity entity){
    Serializable t = super.save(entity);
    return t;
}


public void delete(OaHallEntity entity){
    super.delete(entity);
}


public void saveOrUpdate(OaHallEntity entity){
    super.saveOrUpdate(entity);
}


}