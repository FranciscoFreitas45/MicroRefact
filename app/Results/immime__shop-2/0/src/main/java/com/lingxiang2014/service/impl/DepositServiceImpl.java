package com.lingxiang2014.service.impl;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.DepositDao;
import com.lingxiang2014.entity.Deposit;
import com.lingxiang2014.entity.Deposit.Type;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.DepositService;
@Service("depositServiceImpl")
public class DepositServiceImpl extends BaseServiceImpl<Deposit, Long>implements DepositService{

@Resource(name = "depositDaoImpl")
 private  DepositDao depositDao;


public BigDecimal countBalance(Type type,Date beginDate,Date endDate){
    return depositDao.countBalance(type, beginDate, endDate);
}


@Resource(name = "depositDaoImpl")
public void setBaseDao(DepositDao depositDao){
    super.setBaseDao(depositDao);
}


public List<Deposit> findList(Member member,Type type,Date beginDate,Date endDate){
    return depositDao.findList(member, type, beginDate, endDate);
}


public BigDecimal count(Member member,Type type,Date beginDate,Date endDate){
    return depositDao.count(member, type, beginDate, endDate);
}


@Transactional(readOnly = true)
public Page<Deposit> findPage(Member member,Pageable pageable){
    return depositDao.findPage(member, pageable);
}


}