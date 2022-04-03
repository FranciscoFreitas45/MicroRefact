package com.jeecg.demo.service;
 import com.jeecg.demo.entity.JformOrderMainEntity;
import com.jeecg.demo.entity.JformOrderCustomerEntity;
import com.jeecg.demo.entity.JformOrderTicketEntity;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface JformOrderMainServiceI extends CommonService{


public void updateCustomers(List<JformOrderCustomerEntity> jformOrderCustomerList)
;

public boolean doDelSql(JformOrderMainEntity t)
;

public void updateMain(JformOrderMainEntity jformOrderMain,List<JformOrderCustomerEntity> jformOrderCustomerList,List<JformOrderTicketEntity> jformOrderTicketList)
;

public void delMain(JformOrderMainEntity jformOrderMain)
;

public boolean doUpdateSql(JformOrderMainEntity t)
;

public void addMain(JformOrderMainEntity jformOrderMain,List<JformOrderCustomerEntity> jformOrderCustomerList,List<JformOrderTicketEntity> jformOrderTicketList)
;

public boolean doAddSql(JformOrderMainEntity t)
;

public void delete(T entity)
;

}