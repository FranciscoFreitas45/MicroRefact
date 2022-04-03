package com.uec.imonitor.config.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.config.bean.ConfigPageParamEntity;
public interface IConfigPageParamService {


public ConfigPageParamEntity findById(Integer id)
;

public ConfigPageParamEntity save(ConfigPageParamEntity entity)
;

public ConfigPageParamEntity update(ConfigPageParamEntity entity)
;

public List<ConfigPageParamEntity> listAll()
;

public void delete(Integer id)
;

}