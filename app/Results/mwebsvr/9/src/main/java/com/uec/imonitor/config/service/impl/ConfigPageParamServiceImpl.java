package com.uec.imonitor.config.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.config.bean.ConfigPageParamEntity;
import com.uec.imonitor.config.dao.IConfigPageParamJPARepository;
import com.uec.imonitor.config.service.IConfigPageParamService;
@Service("configPageParamService")
@Transactional(value = "transactionManager")
public class ConfigPageParamServiceImpl extends BaseServiceimplements IConfigPageParamService{

@Autowired
 private  IConfigPageParamJPARepository configPageParamRepository;


@Override
public ConfigPageParamEntity findById(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    ConfigPageParamEntity entity = configPageParamRepository.findOne(id);
    return entity;
}


@Override
public ConfigPageParamEntity save(ConfigPageParamEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "entity" });
    }
    ConfigPageParamEntity save = configPageParamRepository.save(entity);
    return save;
}


@Override
public ConfigPageParamEntity update(ConfigPageParamEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "entity" });
    }
    ConfigPageParamEntity save = configPageParamRepository.save(entity);
    return save;
}


@Override
public List<ConfigPageParamEntity> listAll(){
    List<ConfigPageParamEntity> list = configPageParamRepository.findAll();
    return list;
}


@Override
public void delete(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    configPageParamRepository.delete(id);
}


}