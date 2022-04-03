package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.dao.BankTypeDao;
import com.lingxiang2014.entity.BankType;
import com.lingxiang2014.service.BankTypeService;
@Service("bankTypeServiceImpl")
public class BankTypeServiceImpl extends BaseServiceImpl<BankType, Long>implements BankTypeService{

@Resource(name = "bankTypeDaoImpl")
 private  BankTypeDao bankTypeDao;


@Transactional(readOnly = true)
public boolean usernameExists(String fullName){
    return bankTypeDao.usernameExists(fullName);
}


@Resource(name = "bankTypeDaoImpl")
public void setBaseDao(BankTypeDao bankTypeDao){
    super.setBaseDao(bankTypeDao);
}


}