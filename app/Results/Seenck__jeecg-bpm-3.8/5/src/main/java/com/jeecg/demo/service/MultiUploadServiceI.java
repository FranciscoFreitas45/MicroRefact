package com.jeecg.demo.service;
 import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.demo.entity.MultiUploadEntity;
import java.io.Serializable;
public interface MultiUploadServiceI extends CommonService{


public Serializable save(MultiUploadEntity entity)
;

public void delete(MultiUploadEntity entity)
;

public void saveOrUpdate(MultiUploadEntity entity)
;

}