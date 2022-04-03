package com.lingxiang2014.service;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Withdraw;
import com.lingxiang2014.entity.Withdraw.Status;
public interface WithdrawService extends BaseService<Withdraw, Long>{


public BigDecimal countBalance(Status status,Date beginDate,Date endDate)
;

public List<Withdraw> findList(Member member,Status status,Date beginDate,Date endDate)
;

public BigDecimal count(Member member,Status success,Date beginDate,Date endDate)
;

public Page<Withdraw> findPage(Member member,Status status,Pageable pageable)
;

}