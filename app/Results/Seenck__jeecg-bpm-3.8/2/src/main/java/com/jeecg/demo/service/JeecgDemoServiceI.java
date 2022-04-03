package com.jeecg.demo.service;
 import com.jeecg.demo.entity.JeecgDemoEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface JeecgDemoServiceI extends CommonService{


public Serializable save(JeecgDemoEntity entity)
;

public void jdbcProcedure()
;

public void delete(JeecgDemoEntity entity)
;

public void saveOrUpdate(JeecgDemoEntity entity)
;

public void jdbcBatchSave()
;

}