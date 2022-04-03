package com.lingxiang2014.dao;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Deposit;
import com.lingxiang2014.entity.Deposit.Type;
import com.lingxiang2014.entity.Member;
public interface DepositDao extends BaseDao<Deposit, Long>{


public BigDecimal countBalance(Type type,Date beginDate,Date endDate)
;

public List<Deposit> findList(Member member,Type type,Date beginDate,Date endDate)
;

public BigDecimal count(Member member,Type type,Date beginDate,Date endDate)
;

public Page<Deposit> findPage(Member member,Pageable pageable)
;

}