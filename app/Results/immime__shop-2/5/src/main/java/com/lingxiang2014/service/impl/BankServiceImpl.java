package com.lingxiang2014.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.BankDao;
import com.lingxiang2014.entity.Bank;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.BankService;
@Service("bankServiceImpl")
public class BankServiceImpl extends BaseServiceImpl<Bank, Long>implements BankService{

@Resource(name = "bankDaoImpl")
 private  BankDao bankDao;


public List<Bank> findListByMember(Member member){
    return bankDao.findListByMember(member);
}


@Resource(name = "bankDaoImpl")
public void setBaseDao(BankDao bankDao){
    super.setBaseDao(bankDao);
}


public Page<Bank> findPage(Pageable pageable,Member member){
    return bankDao.findPage(pageable, member);
}


}