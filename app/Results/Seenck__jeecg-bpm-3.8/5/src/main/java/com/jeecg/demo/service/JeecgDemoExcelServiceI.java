package com.jeecg.demo.service;
 import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.demo.entity.JeecgDemoExcelEntity;
public interface JeecgDemoExcelServiceI extends CommonService{


public Serializable save(JeecgDemoExcelEntity entity)
;

public void delete(JeecgDemoExcelEntity entity)
;

public void saveOrUpdate(JeecgDemoExcelEntity entity)
;

}