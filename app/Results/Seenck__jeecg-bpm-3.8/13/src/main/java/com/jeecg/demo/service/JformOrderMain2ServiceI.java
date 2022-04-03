package com.jeecg.demo.service;
 import com.jeecg.demo.entity.JformOrderMain2Entity;
import com.jeecg.demo.entity.JformOrderTicket2Entity;
import com.jeecg.demo.entity.JformOrderCustomer2Entity;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface JformOrderMain2ServiceI extends CommonService{


public boolean doDelSql(JformOrderMain2Entity t)
;

public void updateMain(JformOrderMain2Entity jformOrderMain2,List<JformOrderTicket2Entity> jformOrderTicket2List,List<JformOrderCustomer2Entity> jformOrderCustomer2List)
;

public void delMain(JformOrderMain2Entity jformOrderMain2)
;

public boolean doUpdateSql(JformOrderMain2Entity t)
;

public void addMain(JformOrderMain2Entity jformOrderMain2,List<JformOrderTicket2Entity> jformOrderTicket2List,List<JformOrderCustomer2Entity> jformOrderCustomer2List)
;

public boolean doAddSql(JformOrderMain2Entity t)
;

public void delete(T entity)
;

}