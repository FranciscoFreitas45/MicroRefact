package com.lingxiang2014.dao;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.InCome;
import com.lingxiang2014.entity.Member;
public interface InComeDao extends BaseDao<InCome, Long>{


public List<InCome> findInComeList(Date beginDate,Date endDate)
;

public BigDecimal findInCome(Date beginDate,Date endDate)
;

public Page<InCome> findPage(Member member,Pageable pageable)
;

}