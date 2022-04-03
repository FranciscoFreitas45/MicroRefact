package com.lingxiang2014.service.impl;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.WithdrawDao;
import com.lingxiang2014.entity.Withdraw;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Withdraw.Status;
import com.lingxiang2014.service.WithdrawService;
@Service("withdrawServiceImpl")
public class WithdrawServiceImpl extends BaseServiceImpl<Withdraw, Long>implements WithdrawService{

@Resource(name = "withdrawDaoImpl")
 private  WithdrawDao withdrawDao;


public BigDecimal countBalance(Status status,Date beginDate,Date endDate){
    return withdrawDao.countBalance(status, beginDate, endDate);
}


@Resource(name = "withdrawDaoImpl")
public void setBaseDao(WithdrawDao withdrawDao){
    super.setBaseDao(withdrawDao);
}


public List<Withdraw> findList(Member member,Status status,Date beginDate,Date endDate){
    return withdrawDao.findList(member, status, beginDate, endDate);
}


public BigDecimal count(Member member,Status success,Date beginDate,Date endDate){
    return withdrawDao.count(member, success, beginDate, endDate);
}


@Transactional(readOnly = true)
public Page<Withdraw> findPage(Member member,Status status,Pageable pageable){
    return withdrawDao.findPage(member, status, pageable);
}


}