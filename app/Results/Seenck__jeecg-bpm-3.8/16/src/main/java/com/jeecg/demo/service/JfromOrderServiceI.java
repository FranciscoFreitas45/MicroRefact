package com.jeecg.demo.service;
 import com.jeecg.demo.entity.JfromOrderEntity;
import com.jeecg.demo.entity.JfromOrderLineEntity;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface JfromOrderServiceI extends CommonService{


public boolean doDelSql(JfromOrderEntity t)
;

public void updateMain(JfromOrderEntity jfromOrder,List<JfromOrderLineEntity> jfromOrderLineList)
;

public void delMain(JfromOrderEntity jfromOrder)
;

public boolean doUpdateSql(JfromOrderEntity t)
;

public void addMain(JfromOrderEntity jfromOrder,List<JfromOrderLineEntity> jfromOrderLineList)
;

public boolean doAddSql(JfromOrderEntity t)
;

public void delete(T entity)
;

}