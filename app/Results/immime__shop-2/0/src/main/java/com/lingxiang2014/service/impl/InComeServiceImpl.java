package com.lingxiang2014.service.impl;
 import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.InComeDao;
import com.lingxiang2014.entity.InCome;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.InComeService;
@Service("inComeServiceImpl")
public class InComeServiceImpl extends BaseServiceImpl<InCome, Long>implements InComeService{

@Resource(name = "inComeDaoImpl")
 private  InComeDao inComeDao;


@Resource(name = "inComeDaoImpl")
public void setBaseDao(InComeDao inComeDao){
    super.setBaseDao(inComeDao);
}


public BigDecimal findInCome(Date beginDate,Date endDate){
    return inComeDao.findInCome(beginDate, endDate);
}


@Transactional(readOnly = true)
public Page<InCome> findPage(Member member,Pageable pageable){
    return inComeDao.findPage(member, pageable);
}


}